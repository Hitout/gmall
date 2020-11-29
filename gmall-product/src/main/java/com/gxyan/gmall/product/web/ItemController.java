package com.gxyan.gmall.product.web;

import com.gxyan.gmall.product.service.SkuInfoService;
import com.gxyan.gmall.product.vo.SkuItemVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author gxyan
 * @date 2020/11/24 23:07
 */
@Controller
public class ItemController {
    @Resource
    private SkuInfoService skuInfoService;

    /**
     * 展示当前sku的详情
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable Long skuId, Model model) throws ExecutionException, InterruptedException {
        System.out.println("准备查询" + skuId + "详情");
        SkuItemVo vo = skuInfoService.item(skuId);
        model.addAttribute("item", vo);
        return "item";
    }
}
