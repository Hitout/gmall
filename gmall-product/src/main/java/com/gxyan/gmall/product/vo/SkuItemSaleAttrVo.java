package com.gxyan.gmall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/11/24 23:11
 */
@Data
@ToString
public class SkuItemSaleAttrVo {
    private Long attrId;
    private String attrName;
    private List<AttrValueWithSkuIdVo> attrValues;
}
