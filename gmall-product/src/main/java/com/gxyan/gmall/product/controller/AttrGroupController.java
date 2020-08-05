package com.gxyan.gmall.product.controller;

import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.product.entity.AttrGroupEntity;
import com.gxyan.gmall.product.service.AttrGroupService;
import com.gxyan.gmall.product.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;



/**
 * 属性分组
 *
 * @author gxyan
 * @email gxyan@qq.com
 * @date 2020-07-30 21:31:59
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Resource
    private AttrGroupService attrGroupService;
    @Resource
    private CategoryService categoryService;

    /**
     * 获取分类属性分组列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long catelogId){
        PageUtils page = attrGroupService.queryPage(params,catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 获取属性分组详情
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatelogPath(catelogId);

        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
