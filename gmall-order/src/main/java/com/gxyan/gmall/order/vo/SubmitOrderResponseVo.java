package com.gxyan.gmall.order.vo;

import com.gxyan.gmall.order.entity.OrderEntity;
import lombok.Data;

/**
 * @author gxyan
 * @date 2020/12/7
 */
@Data
public class SubmitOrderResponseVo {
    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;
}
