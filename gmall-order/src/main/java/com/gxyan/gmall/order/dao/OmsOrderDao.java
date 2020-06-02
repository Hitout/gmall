package com.gxyan.gmall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxyan.gmall.order.entity.OmsOrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author gxyan
 * @email sunlightcs@gmail.com
 * @date 2020-05-31 22:48:07
 */
@Mapper
public interface OmsOrderDao extends BaseMapper<OmsOrderEntity> {
	
}
