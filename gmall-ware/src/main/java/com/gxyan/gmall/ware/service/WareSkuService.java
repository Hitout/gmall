package com.gxyan.gmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.to.SkuHasStockVo;
import com.gxyan.gmall.common.to.WareSkuLockVo;
import com.gxyan.gmall.common.to.mq.OrderTo;
import com.gxyan.gmall.common.to.mq.StockLockedTo;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author gxyan
 * @date 2020-07-30 20:25:35
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    Boolean orderLockStock(WareSkuLockVo lockVo);

    void unlockStock(StockLockedTo to);

    void unlockStock(OrderTo orderTo);
}

