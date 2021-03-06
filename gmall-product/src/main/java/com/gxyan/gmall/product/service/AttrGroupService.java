package com.gxyan.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.product.entity.AttrGroupEntity;
import com.gxyan.gmall.product.vo.AttrGroupWithAttrsVo;
import com.gxyan.gmall.product.vo.SpuItemAttrGroupVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author gxyan
 * @date 2020-07-30 21:31:59
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}

