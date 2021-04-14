package com.gxyan.gmall.common.to;

import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/10
 */
@Data
public class WareSkuLockVo {
    private String orderSn;

    private List<OrderItemVo> locks;
}
