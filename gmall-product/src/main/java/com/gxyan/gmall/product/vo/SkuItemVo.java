package com.gxyan.gmall.product.vo;

import com.gxyan.gmall.product.entity.SkuImagesEntity;
import com.gxyan.gmall.product.entity.SkuInfoEntity;
import com.gxyan.gmall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/11/24 23:08
 */
@Data
public class SkuItemVo {
    /**
     * sku基本信息
     */
    SkuInfoEntity info;

    boolean hasStock = true;

    /**
     * sku图片信息
     */
    List<SkuImagesEntity> images;
    /**
     * spu销售属性组合
     */
    List<SkuItemSaleAttrVo> saleAttr;
    /**
     * spu介绍
     */
    SpuInfoDescEntity desp;
    /**
     * spu规格参数信息
     */
    List<SpuItemAttrGroupVo> groupAttrs;
}
