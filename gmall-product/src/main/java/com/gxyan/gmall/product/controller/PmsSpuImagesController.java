package com.gxyan.gmall.product.controller;

import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.product.entity.PmsSpuImagesEntity;
import com.gxyan.gmall.product.service.PmsSpuImagesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * spu图片
 *
 * @author gxyan
 * @date 2020-06-02 22:55:23
 */
@RestController
@RequestMapping("product/pmsspuimages")
public class PmsSpuImagesController {
    @Resource
    private PmsSpuImagesService pmsSpuImagesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsSpuImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PmsSpuImagesEntity pmsSpuImages = pmsSpuImagesService.getById(id);

        return R.ok().put("pmsSpuImages", pmsSpuImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PmsSpuImagesEntity pmsSpuImages){
		pmsSpuImagesService.save(pmsSpuImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PmsSpuImagesEntity pmsSpuImages){
		pmsSpuImagesService.updateById(pmsSpuImages);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		pmsSpuImagesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
