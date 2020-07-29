package com.gxyan.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author gxyan
 * @date 2020-06-02 22:55:23
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

