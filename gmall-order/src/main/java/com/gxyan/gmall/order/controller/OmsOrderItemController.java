package com.gxyan.gmall.order.controller;

import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.order.entity.OmsOrderItemEntity;
import com.gxyan.gmall.order.service.OmsOrderItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 订单项信息
 *
 * @author gxyan
 * @email sunlightcs@gmail.com
 * @date 2020-05-31 22:48:07
 */
@RestController
@RequestMapping("generator/omsorderitem")
public class OmsOrderItemController {
    @Resource
    private OmsOrderItemService omsOrderItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = omsOrderItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OmsOrderItemEntity omsOrderItem = omsOrderItemService.getById(id);

        return R.ok().put("omsOrderItem", omsOrderItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OmsOrderItemEntity omsOrderItem){
		omsOrderItemService.save(omsOrderItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OmsOrderItemEntity omsOrderItem){
		omsOrderItemService.updateById(omsOrderItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		omsOrderItemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
