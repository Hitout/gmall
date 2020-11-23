package com.gxyan.search.controller;

import com.gxyan.gmall.common.exception.BizCodeEnum;
import com.gxyan.gmall.common.to.SkuEsModel;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author gxyan
 * @date 2020/11/23 23:20
 */
@Slf4j
@RestController
@RequestMapping("search/save")
public class ElasticSaveController {
    @Resource
    ProductSaveService productSaveService;

    /**
     * 上架商品
     */
    @PostMapping("product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels){
        boolean b;
        try {
            b = productSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            log.error("ElasticSaveController商品上架错误：{}", e);
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
        }
        if(!b){
            return R.ok();
        } else {
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
        }

    }
}
