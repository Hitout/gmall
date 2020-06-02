package com.gxyan.gmall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.Query;
import com.gxyan.gmall.order.dao.OmsRefundInfoDao;
import com.gxyan.gmall.order.entity.OmsRefundInfoEntity;
import com.gxyan.gmall.order.service.OmsRefundInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("omsRefundInfoService")
public class OmsRefundInfoServiceImpl extends ServiceImpl<OmsRefundInfoDao, OmsRefundInfoEntity> implements OmsRefundInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OmsRefundInfoEntity> page = this.page(
                new Query<OmsRefundInfoEntity>().getPage(params),
                new QueryWrapper<OmsRefundInfoEntity>()
        );

        return new PageUtils(page);
    }

}