package com.gxyan.gmall.product.vo;

import com.gxyan.gmall.common.to.SpuBoundTo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gxyan
 * @date 2020/8/7 21:42
 */
@Data
public class SpuSaveVo {
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private SpuBoundTo bounds;
    private List<BaseAttrs> baseAttrs;
    private List<SkuVo> skus;
}
