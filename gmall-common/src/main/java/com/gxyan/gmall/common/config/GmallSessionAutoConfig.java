package com.gxyan.gmall.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * session自动装配
 * @author gxyan
 * @date 2020/12/3 23:54
 */
@Configuration
@ConditionalOnClass({CookieSerializer.class, RedisSerializer.class})
public class GmallSessionAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // 配置顶级域名，实现子域名session共享
        cookieSerializer.setDomainName("gxmall.com");
        cookieSerializer.setCookieName("GX_SESSION");
        return cookieSerializer;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(Object.class);
    }
}