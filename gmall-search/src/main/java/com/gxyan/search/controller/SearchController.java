package com.gxyan.search.controller;

import com.gxyan.search.service.MallSearchService;
import com.gxyan.search.vo.SearchParam;
import com.gxyan.search.vo.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author gxyan
 * @date 2020/11/22 23:00
 */
@Controller
public class SearchController {
    @Resource
    private MallSearchService mallSearchService;

    @GetMapping("list.html")
    public String list(SearchParam param, Model model, HttpServletRequest request) {
        param.set_queryString(request.getQueryString());
        // 根据传递过来的页面的查询参数，去es检索商品
        SearchResult result = mallSearchService.search(param);
        model.addAttribute("result", result);
        return "list";
    }
}
