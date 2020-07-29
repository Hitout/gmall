package com.gxyan.gmall.member.controller;

import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.member.entity.UmsMemberEntity;
import com.gxyan.gmall.member.feign.CouponFeignService;
import com.gxyan.gmall.member.service.UmsMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员
 *
 * @author gxyan
 * @date 2020-06-02 23:12:41
 */
@RestController
@RequestMapping("member/umsmember")
public class UmsMemberController {
    @Resource
    private UmsMemberService umsMemberService;

    @Resource
    private CouponFeignService couponFeignService;

    @RequestMapping("/couponHello")
    public String couponHello(){
        return couponFeignService.hello();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = umsMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		UmsMemberEntity umsMember = umsMemberService.getById(id);

        return R.ok().put("umsMember", umsMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UmsMemberEntity umsMember){
		umsMemberService.save(umsMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UmsMemberEntity umsMember){
		umsMemberService.updateById(umsMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		umsMemberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
