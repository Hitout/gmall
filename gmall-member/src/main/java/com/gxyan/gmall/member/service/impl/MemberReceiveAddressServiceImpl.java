package com.gxyan.gmall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.Query;
import com.gxyan.gmall.member.dao.MemberReceiveAddressDao;
import com.gxyan.gmall.member.entity.MemberReceiveAddressEntity;
import com.gxyan.gmall.member.service.MemberReceiveAddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author gxyan
 */
@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity> implements MemberReceiveAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberReceiveAddressEntity> page = this.page(
                new Query<MemberReceiveAddressEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MemberReceiveAddressEntity> getAddressByUserId(Long userId) {
        return this.list(new QueryWrapper<MemberReceiveAddressEntity>().eq("member_id", userId));
    }

}