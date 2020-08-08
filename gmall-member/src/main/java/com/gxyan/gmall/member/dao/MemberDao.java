package com.gxyan.gmall.member.dao;

import com.gxyan.gmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author gxyan
 * @date 2020-07-30 20:42:40
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
