package com.gxyan.gmall.coupon.controller;

import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.coupon.entity.SmsCouponEntity;
import com.gxyan.gmall.coupon.service.SmsCouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 优惠券信息
 *
 * @author gxyan
 * @date 2020-06-02 23:06:36
 */
@RestController
@RequestMapping("coupon/smscoupon")
public class SmsCouponController {
    @Resource
    private SmsCouponService smsCouponService;

    @RequestMapping("hello")
    public String hello() {
        return "This is Coupon";
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = smsCouponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SmsCouponEntity smsCoupon = smsCouponService.getById(id);

        return R.ok().put("smsCoupon", smsCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SmsCouponEntity smsCoupon){
		smsCouponService.save(smsCoupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SmsCouponEntity smsCoupon){
		smsCouponService.updateById(smsCoupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		smsCouponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
