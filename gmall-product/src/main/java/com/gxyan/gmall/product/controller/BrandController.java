package com.gxyan.gmall.product.controller;

import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.product.entity.BrandEntity;
import com.gxyan.gmall.product.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 品牌
 *
 * @author gxyan
 * @date 2020-06-02 22:55:23
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity pmsBrand = brandService.getById(brandId);

        return R.ok().put("pmsBrand", pmsBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BrandEntity pmsBrand){
		brandService.save(pmsBrand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BrandEntity pmsBrand){
        brandService.updateById(pmsBrand);

        return R.ok();
    }

    /**
     * 更新显示状态
     */
    @RequestMapping("/update/status")
    public R updateStatus(@RequestBody BrandEntity pmsBrand){
        brandService.updateById(pmsBrand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
