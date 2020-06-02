package com.gxyan.gmall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxyan.gmall.order.entity.OmsOrderItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author gxyan
 * @email sunlightcs@gmail.com
 * @date 2020-05-31 22:48:07
 */
@Mapper
public interface OmsOrderItemDao extends BaseMapper<OmsOrderItemEntity> {
	
}
