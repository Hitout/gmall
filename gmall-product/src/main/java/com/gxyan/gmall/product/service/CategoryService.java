package com.gxyan.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.product.entity.CategoryEntity;
import com.gxyan.gmall.product.vo.Catalog2VO;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author gxyan
 * @date 2020-06-02 22:55:23
 */
public interface CategoryService extends IService<CategoryEntity> {

    /**
     * 获取商品三级分类
     * @return 商品三级分类
     */
    List<CategoryEntity> queryCategoryTree();

    /**
     * 根据子节点获取分类完整路径
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);

    /**
     * 获取分类Json
     * @return
     */
    Map<String, List<Catalog2VO>> getCatalogJson();

    /**
     * 查出所有的一级分类
     * @return
     */
    List<CategoryEntity> getLevel1Categories();
}

