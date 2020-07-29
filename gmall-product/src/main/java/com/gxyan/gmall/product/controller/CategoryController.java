package com.gxyan.gmall.product.controller;

import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.product.entity.CategoryEntity;
import com.gxyan.gmall.product.service.CategoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 商品三级分类
 *
 * @author gxyan
 * @date 2020-06-02 22:55:23
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list/tree")
    public R list(){
        List<CategoryEntity> page = categoryService.queryCategoryTree();

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity pmsCategory = categoryService.getById(catId);

        return R.ok().put("data", pmsCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity pmsCategory){
		categoryService.save(pmsCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity pmsCategory){
		categoryService.updateById(pmsCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
