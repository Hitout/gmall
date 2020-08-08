package com.gxyan.gmall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxyan.gmall.common.to.SkuReductionTo;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.Query;
import com.gxyan.gmall.coupon.dao.SkuFullReductionDao;
import com.gxyan.gmall.coupon.entity.MemberPriceEntity;
import com.gxyan.gmall.coupon.entity.SkuFullReductionEntity;
import com.gxyan.gmall.coupon.entity.SkuLadderEntity;
import com.gxyan.gmall.coupon.service.MemberPriceService;
import com.gxyan.gmall.coupon.service.SkuFullReductionService;
import com.gxyan.gmall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {
    @Resource
    private SkuLadderService skuLadderService;
    @Resource
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSkuReduction(SkuReductionTo reductionTo) {
        //1、sms_sku_ladder
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(reductionTo.getSkuId());
        skuLadderEntity.setFullCount(reductionTo.getFullCount());
        skuLadderEntity.setDiscount(reductionTo.getDiscount());
        skuLadderEntity.setAddOther(reductionTo.getCountStatus());
        if(reductionTo.getFullCount() > 0){
            skuLadderService.save(skuLadderEntity);
        }

        //2、sms_sku_full_reduction
        SkuFullReductionEntity reductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(reductionTo,reductionEntity);
        if(reductionEntity.getFullPrice().compareTo(BigDecimal.ZERO) > 0){
            this.save(reductionEntity);
        }

        //3、sms_member_price
        List<MemberPriceEntity> collect = reductionTo.getMemberPrice()
                .stream().map(item -> {
                    MemberPriceEntity priceEntity = new MemberPriceEntity();
                    priceEntity.setSkuId(reductionTo.getSkuId());
                    priceEntity.setMemberLevelId(item.getId());
                    priceEntity.setMemberLevelName(item.getName());
                    priceEntity.setMemberPrice(item.getPrice());
                    priceEntity.setAddOther(1);
                    return priceEntity;
                }).filter(item-> item.getMemberPrice().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
        memberPriceService.saveBatch(collect);
    }

}