package com.gxyan.gmall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxyan.gmall.order.entity.OmsOrderOperateHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author gxyan
 * @email sunlightcs@gmail.com
 * @date 2020-05-31 22:48:07
 */
@Mapper
public interface OmsOrderOperateHistoryDao extends BaseMapper<OmsOrderOperateHistoryEntity> {
	
}
