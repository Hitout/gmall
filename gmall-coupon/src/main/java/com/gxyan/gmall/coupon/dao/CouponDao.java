package com.gxyan.gmall.coupon.dao;

import com.gxyan.gmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author gxyan
 * @email gxyan@qq.com
 * @date 2020-07-30 21:22:55
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
