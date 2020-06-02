package com.gxyan.gmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.ware.entity.WmsWareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author gxyan
 * @date 2020-06-02 23:15:17
 */
public interface WmsWareSkuService extends IService<WmsWareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

