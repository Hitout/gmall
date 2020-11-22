package com.gxyan.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxyan.gmall.product.dao.CategoryDao;
import com.gxyan.gmall.product.entity.CategoryEntity;
import com.gxyan.gmall.product.service.CategoryService;
import com.gxyan.gmall.product.vo.Catalog2VO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> parentPath = findParentPath(catelogId, new ArrayList<>());
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[0]);
    }

    @Override
    @Cacheable(value = {"category"},key = "#root.method.name",sync = true)
    public List<CategoryEntity> getLevel1Categories() {
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
    }

    @Override
    @Cacheable(value = "category",key = "#root.methodName")
    public Map<String, List<Catalog2VO>> getCatalogJson() {
        // 查出所有的分类数据，再做封装
        List<CategoryEntity> selectList = baseMapper.selectList(null);

        // 1、查出一级分类
        List<CategoryEntity> level1Categories = getParentCid(selectList, 0L);

        return level1Categories.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), level1 -> {
            // 2、查出当前一级分类包含的二级分类
            List<CategoryEntity> level2Categories = getParentCid(selectList, level1.getCatId());

            return level2Categories.stream().map(level2 -> {
                // 封装二级分类
                Catalog2VO catalog2VO = new Catalog2VO(level1.getCatId().toString(), null, level2.getCatId().toString(), level2.getName());

                // 3、查出当前二级分类包含的三级分类
                List<CategoryEntity> level3Categories = getParentCid(selectList, level2.getCatId());
                List<Catalog2VO.Catalog3> category3Vos = level3Categories.stream().map(level3 -> {
                    // 封装三级分类
                    return new Catalog2VO.Catalog3(level2.getCatId().toString(), level3.getCatId().toString(), level3.getName());
                }).collect(Collectors.toList());
                catalog2VO.setCatalog3List(category3Vos);
                return catalog2VO;
            }).collect(Collectors.toList());
        }));
    }

    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, long parentCid) {
        return selectList.stream().filter(item -> item.getParentCid().equals(parentCid)).collect(Collectors.toList());
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        CategoryEntity category = this.getById(catelogId);
        if (category.getParentCid()!=0) {
            findParentPath(category.getParentCid(), paths);
        }
        return paths;
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