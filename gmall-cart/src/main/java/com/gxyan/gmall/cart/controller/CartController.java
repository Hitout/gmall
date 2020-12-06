package com.gxyan.gmall.cart.controller;

import com.gxyan.gmall.cart.service.CartService;
import com.gxyan.gmall.cart.vo.Cart;
import com.gxyan.gmall.cart.vo.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/5 17:49
 */
@Controller
public class CartController {
    @Resource
    private CartService cartService;

    @GetMapping("/cart.html")
    public String cartListPage(Model model) {

        Cart cart = cartService.getCart();
        model.addAttribute("cart",cart);
        return "cartList";
    }

    /**
     * 添加购物车
     */
    @GetMapping("/addCartItem")
    public String addCartItem(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            RedirectAttributes attributes) {

        cartService.addCartItem(skuId,num);
        //RedirectAttributes，重定向时会将参数放到地址后
        attributes.addAttribute("skuId",skuId);
        return "redirect:http://cart.gxmall.com/addCartItemSuccess";
    }

    @GetMapping("/addCartItemSuccess")
    public String addCartItemSuccess(@RequestParam("skuId") Long skuId, Model model) {
        CartItem cartItem = cartService.getCartItem(skuId);
        model.addAttribute("item", cartItem);
        return "success";
    }

    @GetMapping("/countItem")
    public String changeItemCount(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.changeItemCount(skuId, num);
        return "redirect:http://cart.gxmall.com/cart.html";
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return "redirect:http://cart.gxmall.com/cart.html";
    }

    @ResponseBody
    @GetMapping("/getCheckedItems")
    public List<CartItem> getCheckedItems() {
        return cartService.getCheckedItems();
    }
}
