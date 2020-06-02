package com.gxyan.gmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.coupon.entity.SmsHomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author gxyan
 * @date 2020-06-02 23:06:37
 */
public interface SmsHomeAdvService extends IService<SmsHomeAdvEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

