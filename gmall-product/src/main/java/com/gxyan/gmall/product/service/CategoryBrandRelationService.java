package com.gxyan.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.product.entity.BrandEntity;
import com.gxyan.gmall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author gxyan
 * @date 2020-07-30 21:31:59
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BrandEntity> getBrandsByCatId(Long catId);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);
}

