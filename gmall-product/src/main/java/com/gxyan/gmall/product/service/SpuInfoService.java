package com.gxyan.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.product.entity.SpuInfoEntity;
import com.gxyan.gmall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author gxyan
 * @date 2020-07-30 21:31:59
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo spuSaveVo);
}

