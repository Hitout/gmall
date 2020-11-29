package com.gxyan.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gxyan
 * @date 2020/11/29 23:49
 */
@Configuration
public class GmallWebConfig implements WebMvcConfigurer {
    /**
     * 视图映射
     * 相当于`@GetMapping("/reg.html")public String xxx(){return "reg";}`可添加多个
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/reg.html").setViewName("reg");
        //registry.addViewController("/login.html").setViewName("login");
    }
}
