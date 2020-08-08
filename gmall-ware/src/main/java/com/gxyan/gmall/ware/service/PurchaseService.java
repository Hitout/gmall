package com.gxyan.gmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.ware.entity.PurchaseEntity;
import com.gxyan.gmall.ware.vo.MergeVo;
import com.gxyan.gmall.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author gxyan
 * @date 2020-07-30 20:25:35
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    PageUtils queryPageUnReceivePurchase(Map<String, Object> params);

    void received(List<Long> ids);

    void done(PurchaseDoneVo doneVo);
}

