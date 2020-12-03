package com.gxyan.gmall.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * SpringSession使用：<p>
 * https://docs.spring.io/spring-session/docs/2.3.0.RELEASE/reference/html5/guides/boot-redis.html<p>
 * 1、添加依赖<p>
 * 2、在application.yml中配置redis进行session存储：spring.session.store-type=redis<p>
 * 3、添加注解@EnableRedisHttpSession<p>
 * <br>
 * SpringSession配置类<p>
 * 在此项目中使用spring.factories实现自动装配，此类不启用<p>
 * 项目使用{@link GmallSessionAutoConfig}进行配置
 * @author gxyan
 * @date 2020/11/29 23:30
 */
//@Configuration
public class GmallSessionConfig {
    /**
     * cookie配置：https://docs.spring.io/spring-session/docs/2.3.0.RELEASE/reference/html5/index.html#api-cookieserializer
     */
    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // 配置顶级域名，实现子域名session共享
        cookieSerializer.setDomainName("gxmall.com");
        cookieSerializer.setCookieName("GX_SESSION");
        return cookieSerializer;
    }

    /**
     * JSON序列化配置
     */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
