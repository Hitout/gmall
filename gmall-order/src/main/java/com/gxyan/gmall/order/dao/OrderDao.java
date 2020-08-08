package com.gxyan.gmall.order.dao;

import com.gxyan.gmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author gxyan
 * @date 2020-07-30 21:03:38
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
