package com.gxyan.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.Query;
import com.gxyan.gmall.product.dao.BrandDao;
import com.gxyan.gmall.product.dao.CategoryBrandRelationDao;
import com.gxyan.gmall.product.dao.CategoryDao;
import com.gxyan.gmall.product.entity.BrandEntity;
import com.gxyan.gmall.product.entity.CategoryBrandRelationEntity;
import com.gxyan.gmall.product.entity.CategoryEntity;
import com.gxyan.gmall.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {
    @Resource
    private BrandDao brandDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private CategoryBrandRelationDao relationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BrandEntity> getBrandsByCatId(Long catId) {
        List<CategoryBrandRelationEntity> relationEntityList = relationDao.selectList(
                new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        List<Long> brandIdList = relationEntityList.stream()
                .map(CategoryBrandRelationEntity::getBrandId)
                .collect(Collectors.toList());
        return brandDao.selectBatchIds(brandIdList);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity relationEntity) {
        BrandEntity brandEntity = brandDao.selectById(relationEntity.getBrandId());
        CategoryEntity categoryEntity = categoryDao.selectById(relationEntity.getCatelogId());

        relationEntity.setBrandName(brandEntity.getName());
        relationEntity.setCatelogName(categoryEntity.getName());

        this.save(relationEntity);
    }

}