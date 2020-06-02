package com.gxyan.gmall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxyan.gmall.coupon.entity.SmsSeckillSessionEntity;
import com.gxyan.gmall.coupon.service.SmsSeckillSessionService;
import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import javax.annotation.Resource;

/**
 * 秒杀活动场次
 *
 * @author gxyan
 * @date 2020-06-02 23:06:36
 */
@RestController
@RequestMapping("coupon/smsseckillsession")
public class SmsSeckillSessionController {
    @Resource
    private SmsSeckillSessionService smsSeckillSessionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = smsSeckillSessionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SmsSeckillSessionEntity smsSeckillSession = smsSeckillSessionService.getById(id);

        return R.ok().put("smsSeckillSession", smsSeckillSession);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SmsSeckillSessionEntity smsSeckillSession){
		smsSeckillSessionService.save(smsSeckillSession);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SmsSeckillSessionEntity smsSeckillSession){
		smsSeckillSessionService.updateById(smsSeckillSession);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		smsSeckillSessionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
