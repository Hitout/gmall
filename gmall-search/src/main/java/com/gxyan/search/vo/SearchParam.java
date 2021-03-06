package com.gxyan.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/11/22 23:09
 */
@Data
public class SearchParam {
    /**
     * 全文匹配关键字
     */
    private String keyword;
    /**
     * 三级分类id
     */
    private Long catalog3Id;
    /**
     * 排序条件
     */
    private String sort;
    /**
     * 是否只显示有货
     */
    private Integer hasStock;
    /**
     * 价格区间查询
     */
    private String skuPrice;
    /**
     * 品牌id
     */
    private List<Long> brandId;
    /**
     * 按照属性进行筛选
     */
    private List<String> attrs;
    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 原生的所有查询条件
     */
    private String _queryString;
}
