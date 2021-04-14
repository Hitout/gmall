package com.gxyan.gmall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gxyan
 * @date 2020/12/9
 */
@Data
public class FareVo {
    private MemberAddressVo address;
    private BigDecimal fare;
}
