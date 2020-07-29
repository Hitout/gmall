package com.gxyan.gmall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxyan.gmall.product.entity.BrandEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 * 
 * @author gxyan
 * @date 2020-06-02 22:55:23
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
