package com.gxyan.gmall.order.vo;

import com.gxyan.gmall.order.entity.OrderEntity;
import com.gxyan.gmall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/9
 */
@Data
public class OrderCreateTo {
    private OrderEntity order;

    private List<OrderItemEntity> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;
}
