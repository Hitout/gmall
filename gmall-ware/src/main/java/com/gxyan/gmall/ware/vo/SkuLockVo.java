package com.gxyan.gmall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/12
 */
@Data
public class SkuLockVo {
    private Long skuId;
    private Integer num;
    private List<Long> wareIds;
}
