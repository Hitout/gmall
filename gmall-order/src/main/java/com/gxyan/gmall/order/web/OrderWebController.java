package com.gxyan.gmall.order.web;

import com.gxyan.gmall.common.exception.ServiceException;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.order.service.OrderService;
import com.gxyan.gmall.order.vo.OrderConfirmVo;
import com.gxyan.gmall.order.vo.OrderSubmitVo;
import com.gxyan.gmall.order.vo.PayVo;
import com.gxyan.gmall.order.vo.SubmitOrderResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gxyan
 * @date 2020/12/6 23:05
 */
@Controller
public class OrderWebController {
    @Resource
    private OrderService orderService;

    /**
     * 结算
     */
    @GetMapping("/toTrade")
    public String toTrade(Model model) {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        //展示订单确认的数据
        model.addAttribute("confirmOrder", confirmVo);
        return "confirm";
    }

    /**
     * 下单
     */
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo submitVo, Model model, RedirectAttributes attributes) {

        try{
            SubmitOrderResponseVo responseVo = orderService.submitOrder(submitVo);
            Integer code = responseVo.getCode();
            if (code==0){
                model.addAttribute("order", responseVo.getOrder());
                return "pay";
            }else {
                String msg = "下单失败;";
                switch (code) {
                    case 1:
                        msg += "防重令牌校验失败";
                        break;
                    case 2:
                        msg += "商品价格发生变化";
                        break;
                    default:
                }
                attributes.addFlashAttribute("msg", msg);
                return "redirect:http://order.gxmall.com/toTrade";
            }
        } catch (ServiceException e){
            attributes.addFlashAttribute("msg", e.getMsg());
            return "redirect:http://order.gxmall.com/toTrade";
        }
    }

    @GetMapping(value = "/payOrder")
    public String aliPayOrder(@RequestParam("orderSn") String orderSn) {
        System.out.println("接收到订单信息orderSn："+orderSn);
        PayVo payVo = orderService.getOrderPay(orderSn);
        orderService.handlePayResult(payVo);
        return "redirect:http://order.gxmall.com/memberOrder.html";
    }

    /**
     * 获取当前用户的所有订单
     * @return
     */
    @RequestMapping("/memberOrder.html")
    public String memberOrder(@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                              Model model){
        Map<String, Object> params = new HashMap<>();
        params.put("page", pageNum.toString());
        PageUtils page = orderService.getMemberOrderPage(params);
        model.addAttribute("pageUtil", page);
        return "list";
    }
}
