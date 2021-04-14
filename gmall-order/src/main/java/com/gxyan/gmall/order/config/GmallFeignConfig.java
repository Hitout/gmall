package com.gxyan.gmall.order.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gxyan
 * @date 2020/12/6 23:25
 */
@Configuration
public class GmallFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            //1. 使用RequestContextHolder拿到老请求的请求数据
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                //2. 将老请求得到cookie信息放到feign请求上
                String cookie = request.getHeader("Cookie");
                template.header("Cookie", cookie);
            }
        };
    }
}
