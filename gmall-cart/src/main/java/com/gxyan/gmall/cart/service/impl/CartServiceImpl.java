package com.gxyan.gmall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gxyan.gmall.cart.feign.ProductFeignService;
import com.gxyan.gmall.cart.intercepter.CartInterceptor;
import com.gxyan.gmall.cart.service.CartService;
import com.gxyan.gmall.cart.vo.Cart;
import com.gxyan.gmall.cart.vo.CartItem;
import com.gxyan.gmall.cart.vo.SkuInfoVo;
import com.gxyan.gmall.cart.vo.UserInfoTo;
import com.gxyan.gmall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author gxyan
 * @date 2020/12/5 17:58
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProductFeignService productFeignService;
    @Autowired
    private ThreadPoolExecutor executor;

    private final String CART_PREFIX = "gmall:cart:";

    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String string = (String) cartOps.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(string, CartItem.class);
        return cartItem;
    }

    @Override
    public Cart  getCart() {
        Cart cart = new Cart();
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        //1 用户未登录，直接通过user-key获取临时购物车
        List<CartItem> tempCart = getCartByKey(userInfoTo.getUserKey());
        if (userInfoTo.getUserId() == null) {
            cart.setItems(tempCart);
        }else {
            //2 用户登录
            //2.1 查询userId对应的购物车
            List<CartItem> userCart = getCartByKey(userInfoTo.getUserId().toString());
            //2.2 查询user-key对应的临时购物车，并和用户购物车合并
            if (tempCart!=null&&tempCart.size()>0){
                BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(CART_PREFIX + userInfoTo.getUserId());
                for (CartItem cartItem : tempCart) {
                    userCart.add(cartItem);
                    //2.3 在redis中更新数据
                    addCartItem(cartItem.getSkuId(), cartItem.getCount());
                }
            }
            cart.setItems(userCart);
            //2.4 删除临时购物车数据
            redisTemplate.delete(CART_PREFIX + userInfoTo.getUserKey());
        }
        return cart;
    }

    @Override
    public CartItem addCartItem(Long skuId, Integer num) {
        BoundHashOperations<String, Object, Object> ops = getCartOps();
        // 判断当前商品是否已经存在购物车
        String cartJson = (String) ops.get(skuId.toString());
        // 1 已经存在购物车，将数据取出并添加商品数量
        if (!StringUtils.isEmpty(cartJson)) {
            //1.1 将json转为对象并将count+
            CartItem cartItem = JSON.parseObject(cartJson, CartItem.class);
            cartItem.setCount(cartItem.getCount() + num);
            //1.2 将更新后的对象转为json并存入redis
            String jsonString = JSON.toJSONString(cartItem);
            ops.put(skuId.toString(), jsonString);
            return cartItem;
        } else {
            CartItem cartItem = new CartItem();
            // 2 未存在购物车，则添加新商品
            CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
                //2.1 远程查询sku基本信息
                R info = productFeignService.info(skuId);
                SkuInfoVo skuInfo = info.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                });
                cartItem.setCheck(true);
                cartItem.setCount(num);
                cartItem.setImage(skuInfo.getSkuDefaultImg());
                cartItem.setPrice(skuInfo.getPrice());
                cartItem.setSkuId(skuId);
                cartItem.setTitle(skuInfo.getSkuTitle());
            }, executor);

            //2.2 远程查询sku属性组合信息
            CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
                List<String> attrValuesAsString = productFeignService.getSkuSaleAttrValuesAsString(skuId);
                cartItem.setSkuAttr(attrValuesAsString);
            }, executor);

            try {
                CompletableFuture.allOf(future1, future2).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            //2.3 将该属性封装并存入redis,登录用户使用userId为key,否则使用user-key
            String str = JSON.toJSONString(cartItem);
            ops.put(skuId.toString(), str);
            return cartItem;
        }
    }

    @Override
    public void changeItemCount(Long skuId, Integer num) {
        BoundHashOperations<String, Object, Object> ops = getCartOps();
        String cartJson = (String) ops.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(cartJson, CartItem.class);
        cartItem.setCount(num);
        ops.put(skuId.toString(),JSON.toJSONString(cartItem));
    }

    @Override
    public void deleteItem(Long skuId) {
        BoundHashOperations<String, Object, Object> ops = getCartOps();
        ops.delete(skuId.toString());
    }

    @Override
    public List<CartItem> getCheckedItems() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        List<CartItem> cartByKey = getCartByKey(userInfoTo.getUserId().toString());
        return cartByKey.stream().filter(CartItem::getCheck).collect(Collectors.toList());
    }

    private List<CartItem> getCartByKey(String cartKey){
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(CART_PREFIX + cartKey);
        List<Object> values = operations.values();
        if(values != null && values.size() > 0){
            return values.stream().map((obj) -> {
                String str = (String) obj;
                return JSON.parseObject(str, CartItem.class);
            }).collect(Collectors.toList());
        }
        return null;
    }

    private BoundHashOperations<String, Object, Object> getCartOps() {
        //1判断是否已经登录
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        //1.1 登录使用userId操作redis
        if (userInfoTo.getUserId() != null) {
            return redisTemplate.boundHashOps(CART_PREFIX + userInfoTo.getUserId());
        } else {
            //1.2 未登录使用user-key操作redis
            return redisTemplate.boundHashOps(CART_PREFIX + userInfoTo.getUserKey());
        }
    }
}
