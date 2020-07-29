package com.gxyan.gmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxyan.gmall.product.dao.CategoryDao;
import com.gxyan.gmall.product.entity.CategoryEntity;
import com.gxyan.gmall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author gxyan
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public List<CategoryEntity> queryCategoryTree() {
        List<CategoryEntity> categoryList = baseMapper.selectList(null);
        return categoryList.stream()
                // 一级菜单
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0L)
                .map(categoryEntity -> {
                    // 添加子菜单
                    categoryEntity.setChildren(getChildren(categoryEntity, categoryList));
                    return categoryEntity;
                }).sorted(Comparator.comparingInt(menu -> menu.getSort() == null ? 0 : menu.getSort()))
                .collect(Collectors.toList());
    }

    /**
     * 获取子菜单
     * @param root 上级菜单
     * @param categoryList 菜单列表
     * @return 子菜单列表
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> categoryList) {
        List<CategoryEntity> children = categoryList.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildren(categoryEntity, categoryList));
                    return categoryEntity;
                }).sorted(Comparator.comparingInt(menu -> menu.getSort() == null ? 0 : menu.getSort()))
                .collect(Collectors.toList());
        return children;
    }

}