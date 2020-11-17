package com.gxyan.gmall.product.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 二级分类
 * @author gxyan
 * @date 2020/11/17 23:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog2VO {
    /**
     * 一级父分类ID
     */
    private String catalog1Id;
    /**
     * 三级子分类
     */
    private List<Catalog3> catalog3List;
    private String id;
    private String name;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Catalog3{
        private String catalog2Id;
        private String id;
        private String name;
    }
}
