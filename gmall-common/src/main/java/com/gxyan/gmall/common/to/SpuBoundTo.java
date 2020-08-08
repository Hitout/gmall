package com.gxyan.gmall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gxyan
 * @date 2020/8/7 23:35
 */
@Data
public class SpuBoundTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
