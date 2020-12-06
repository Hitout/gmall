package com.gxyan.gmall.cart.service;

import com.gxyan.gmall.cart.vo.Cart;
import com.gxyan.gmall.cart.vo.CartItem;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/5 17:57
 */
public interface CartService {
    CartItem getCartItem(Long skuId);

    Cart getCart();

    CartItem addCartItem(Long skuId, Integer num);

    void changeItemCount(Long skuId, Integer num);

    void deleteItem(Long skuId);

    List<CartItem> getCheckedItems();
}
