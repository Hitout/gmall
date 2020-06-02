package com.gxyan.gmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.member.entity.UmsMemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author gxyan
 * @date 2020-06-02 23:12:41
 */
public interface UmsMemberService extends IService<UmsMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

