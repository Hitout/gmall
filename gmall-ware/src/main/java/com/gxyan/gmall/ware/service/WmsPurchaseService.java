package com.gxyan.gmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.ware.entity.WmsPurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author gxyan
 * @date 2020-06-02 23:15:18
 */
public interface WmsPurchaseService extends IService<WmsPurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

