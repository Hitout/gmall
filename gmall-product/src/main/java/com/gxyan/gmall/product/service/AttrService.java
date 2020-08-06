package com.gxyan.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.product.entity.AttrEntity;
import com.gxyan.gmall.product.vo.AttrGroupRelationVo;
import com.gxyan.gmall.product.vo.AttrRespVo;
import com.gxyan.gmall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author gxyan
 * @email gxyan@qq.com
 * @date 2020-07-30 21:31:59
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    void saveAttr(AttrVo attr);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrGroupId);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrGroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);
}

