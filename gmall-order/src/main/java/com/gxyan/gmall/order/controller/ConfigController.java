package com.gxyan.gmall.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gxyan
 * @date 2020/6/9 22:28
 */
@RefreshScope
@RestController
@RequestMapping("config")
public class ConfigController {

    @Value("${useLocalCache}")
    private String useLocalCache;

    /**
     * http://localhost:7030/config/get
     */
    @RequestMapping("/get")
    public String get() {
        return useLocalCache;
    }
}
