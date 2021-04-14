package com.gxyan.gmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.order.entity.OrderEntity;
import com.gxyan.gmall.order.vo.OrderConfirmVo;
import com.gxyan.gmall.order.vo.OrderSubmitVo;
import com.gxyan.gmall.order.vo.PayVo;
import com.gxyan.gmall.order.vo.SubmitOrderResponseVo;

import java.util.Map;

/**
 * 订单
 *
 * @author gxyan
 * @date 2020-07-30 21:03:38
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderConfirmVo confirmOrder();

    SubmitOrderResponseVo submitOrder(OrderSubmitVo submitVo);

    PayVo getOrderPay(String orderSn);

    void handlePayResult(PayVo payVo);

    PageUtils getMemberOrderPage(Map<String, Object> params);

    void closeOrder(OrderEntity orderEntity);

    OrderEntity getOrderByOrderSn(String orderSn);
}

