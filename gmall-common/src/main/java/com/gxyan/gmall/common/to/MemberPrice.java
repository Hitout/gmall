package com.gxyan.gmall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gxyan
 * @date 2020/8/7 21:45
 */
@Data
public class MemberPrice {
    private Long id;
    private String name;
    private BigDecimal price;
}
