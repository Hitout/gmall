package com.gxyan.gmall.product.web;

import com.gxyan.gmall.product.entity.CategoryEntity;
import com.gxyan.gmall.product.service.CategoryService;
import com.gxyan.gmall.product.vo.Catalog2VO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gxyan
 * @date 2020/11/16 22:47
 */
@Controller
public class IndexController {
    @Resource
    private CategoryService categoryService;

    @GetMapping({"/","/index","/index.html"})
    public String indexPage(Model model){
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categories();
        model.addAttribute("categories", categoryEntities);
        return "index";
    }

    @ResponseBody
    @GetMapping("/index/json/catalog.json")
    public Map<String, List<Catalog2VO>> getCatalogJson(){
        return categoryService.getCatalogJson();
    }
}
