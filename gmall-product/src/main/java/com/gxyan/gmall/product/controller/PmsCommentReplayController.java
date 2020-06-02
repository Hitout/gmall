package com.gxyan.gmall.product.controller;

import com.gxyan.gmall.common.utils.PageUtils;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.product.entity.PmsCommentReplayEntity;
import com.gxyan.gmall.product.service.PmsCommentReplayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author gxyan
 * @date 2020-06-02 22:55:24
 */
@RestController
@RequestMapping("product/pmscommentreplay")
public class PmsCommentReplayController {
    @Resource
    private PmsCommentReplayService pmsCommentReplayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsCommentReplayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PmsCommentReplayEntity pmsCommentReplay = pmsCommentReplayService.getById(id);

        return R.ok().put("pmsCommentReplay", pmsCommentReplay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PmsCommentReplayEntity pmsCommentReplay){
		pmsCommentReplayService.save(pmsCommentReplay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PmsCommentReplayEntity pmsCommentReplay){
		pmsCommentReplayService.updateById(pmsCommentReplay);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		pmsCommentReplayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
