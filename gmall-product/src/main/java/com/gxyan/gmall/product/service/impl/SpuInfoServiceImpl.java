package com.gxyan.gmall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxyan.gmall.common.constant.ProductStatusEnum;
import com.gxyan.gmall.common.to.SkuEsModel;
import com.gxyan.gmall.common.to.SkuHasStockVo;
import com.gxyan.gmall.common.to.SkuReductionTo;
import com.gxyan.gmall.common.to.SpuBoundTo;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.Query;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.product.dao.SkuInfoDao;
import com.gxyan.gmall.product.dao.SpuInfoDao;
import com.gxyan.gmall.product.dao.SpuInfoDescDao;
import com.gxyan.gmall.product.entity.*;
import com.gxyan.gmall.product.feign.CouponFeignService;
import com.gxyan.gmall.product.feign.SearchFeignService;
import com.gxyan.gmall.product.feign.WareFeignService;
import com.gxyan.gmall.product.service.*;
import com.gxyan.gmall.product.vo.Images;
import com.gxyan.gmall.product.vo.SkuVo;
import com.gxyan.gmall.product.vo.SpuSaveVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author gxyan
 */
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Resource
    private SpuInfoDescDao spuInfoDescDao;
    @Resource
    private SpuImagesService spuImagesService;
    @Resource
    private AttrService attrService;
    @Resource
    private ProductAttrValueService attrValueService;
    @Resource
    private CouponFeignService couponFeignService;
    @Resource
    private SkuInfoDao skuInfoDao;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private BrandService brandService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private WareFeignService wareFeignService;
    @Resource
    private SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        // 1.保存spu基本信息 pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.baseMapper.insert(spuInfoEntity);

        // 2.保存Spu的描述图片 pms_spu_info_desc
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        spuInfoDescEntity.setDecript(String.join(",", spuSaveVo.getDecript()));
        spuInfoDescDao.insert(spuInfoDescEntity);

        // 3.保存spu的图片集 pms_spu_images
        List<SpuImagesEntity> imageList = spuSaveVo.getImages().stream().map(img -> {
            SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
            spuImagesEntity.setSpuId(spuInfoEntity.getId());
            spuImagesEntity.setImgUrl(img);
            return spuImagesEntity;
        }).collect(Collectors.toList());
        spuImagesService.saveBatch(imageList);

        // 4.保存spu的规格参数 pms_product_attr_value
        List<ProductAttrValueEntity> attrValueList = spuSaveVo.getBaseAttrs().stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            AttrEntity attrEntity = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(attrEntity.getAttrName());
            valueEntity.setAttrId(attr.getAttrId());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(spuInfoEntity.getId());
            return valueEntity;
        }).collect(Collectors.toList());
        attrValueService.saveBatch(attrValueList);

        // 5.保存spu的积分信息 sms_spu_bounds
        SpuBoundTo spuBoundTo = spuSaveVo.getBounds();
        spuBoundTo.setSpuId(spuInfoEntity.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if(r.getCode() != 0){
            log.error("远程保存spu积分信息失败");
        }

        // 6.保存当前spu对应的所有sku信息
        List<SkuVo> skus = spuSaveVo.getSkus();
        if (CollectionUtils.isEmpty(skus)) {
            return;
        }
        skus.forEach(item -> {
            // 6.1 保存sku的基本信息 pms_sku_info
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            BeanUtils.copyProperties(item,skuInfoEntity);
            skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
            skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
            skuInfoEntity.setSaleCount(0L);
            skuInfoEntity.setSpuId(spuInfoEntity.getId());
            String defaultImg = "";
            for (Images image : item.getImages()) {
                if(image.getDefaultImg() == 1){
                    defaultImg = image.getImgUrl();
                    break;
                }
            }
            skuInfoEntity.setSkuDefaultImg(defaultImg);
            skuInfoDao.insert(skuInfoEntity);

            Long skuId = skuInfoEntity.getSkuId();
            // 6.2 保存sku的图片信息 pms_sku_image
            List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                skuImagesEntity.setSkuId(skuId);
                skuImagesEntity.setImgUrl(img.getImgUrl());
                skuImagesEntity.setDefaultImg(img.getDefaultImg());
                return skuImagesEntity;
            }).filter(entity-> StringUtils.isNotEmpty(entity.getImgUrl())).collect(Collectors.toList());
            skuImagesService.saveBatch(imagesEntities);

            // 6.3 保存sku的销售属性信息 pms_sku_sale_attr_value
            List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = item.getAttr().stream().map(attr -> {
                SkuSaleAttrValueEntity attrValueEntity = new SkuSaleAttrValueEntity();
                BeanUtils.copyProperties(attr, attrValueEntity);
                attrValueEntity.setSkuId(skuId);
                return attrValueEntity;
            }).collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

            // 6.4 保存sku的优惠、满减等信息 sms_sku_ladder\sms_sku_full_reduction\sms_member_price
            SkuReductionTo skuReductionTo = new SkuReductionTo();
            BeanUtils.copyProperties(item,skuReductionTo);
            skuReductionTo.setSkuId(skuId);
            if(skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(BigDecimal.ZERO) > 0){
                R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                if(r1.getCode() != 0){
                    log.error("远程保存sku优惠信息失败");
                }
            }
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void spuInfoUp(Long spuId) {
        //1、查出当前spuId对应的所有sku信息,品牌的名字
        List<SkuInfoEntity> skuInfoEntities = skuInfoService.getSkusBySpuId(spuId);

        //TODO 4、查出当前sku的所有可以被用来检索的规格属性
        List<ProductAttrValueEntity> baseAttrs = productAttrValueService.baseAttrListForSpu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(ProductAttrValueEntity::getAttrId).collect(Collectors.toList());
        List<Long> searchAttrIds = attrService.selectSearchAttrs(attrIds);
        //转换为Set集合
        Set<Long> idSet = new HashSet<>(searchAttrIds);

        List<SkuEsModel.Attrs> attrsList = baseAttrs.stream()
                .filter(item -> idSet.contains(item.getAttrId())).map(item -> {
                    SkuEsModel.Attrs attrs = new SkuEsModel.Attrs();
                    BeanUtils.copyProperties(item, attrs);
                    return attrs;
                }).collect(Collectors.toList());

        List<Long> skuIdList = skuInfoEntities.stream()
                .map(SkuInfoEntity::getSkuId)
                .collect(Collectors.toList());
        //TODO 1、发送远程调用，库存系统查询是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            R skuHasStock = wareFeignService.getSkuHasStock(skuIdList);
            TypeReference<List<SkuHasStockVo>> typeReference = new TypeReference<List<SkuHasStockVo>>() {};
            stockMap = skuHasStock.getData(typeReference).stream()
                    .collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
        } catch (Exception e) {
            log.error("库存服务查询异常：原因{}",e);
        }

        //2、封装每个sku的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> collect = skuInfoEntities.stream().map(sku -> {
            //组装需要的数据
            SkuEsModel esModel = new SkuEsModel();
            esModel.setSkuPrice(sku.getPrice());
            esModel.setSkuImg(sku.getSkuDefaultImg());

            //设置库存信息
            if (finalStockMap == null) {
                esModel.setHasStock(true);
            } else {
                esModel.setHasStock(finalStockMap.get(sku.getSkuId()));
            }

            //TODO 2、热度评分。0
            esModel.setHotScore(0L);

            //TODO 3、查询品牌和分类的名字信息
            BrandEntity brandEntity = brandService.getById(sku.getBrandId());
            esModel.setBrandName(brandEntity.getName());
            esModel.setBrandId(brandEntity.getBrandId());
            esModel.setBrandImg(brandEntity.getLogo());

            CategoryEntity categoryEntity = categoryService.getById(sku.getCatalogId());
            esModel.setCatalogId(categoryEntity.getCatId());
            esModel.setCatalogName(categoryEntity.getName());

            //设置检索属性
            esModel.setAttrs(attrsList);

            BeanUtils.copyProperties(sku,esModel);

            return esModel;
        }).collect(Collectors.toList());

        //TODO 5、将数据发给es进行保存：gxmall-search
        R r = searchFeignService.productStatusUp(collect);

        if (r.getCode() == 0) {
            //远程调用成功
            //TODO 6、修改当前spu的状态
            baseMapper.updateSpuStatus(spuId, ProductStatusEnum.SPU_UP.getCode());
        } else {
            //远程调用失败
            //TODO 7、重复调用？接口幂等性:重试机制
        }
    }

    @Override
    public SpuInfoEntity getSpuBySkuId(Long skuId) {
        SkuInfoEntity skuInfoEntity = skuInfoService.getById(skuId);
        SpuInfoEntity spu = this.getById(skuInfoEntity.getSpuId());
        BrandEntity brandEntity = brandService.getById(spu.getBrandId());
        spu.setBrandName(brandEntity.getName());
        return spu;
    }
}