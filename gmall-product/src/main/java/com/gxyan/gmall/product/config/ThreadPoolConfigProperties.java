package com.gxyan.gmall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author gxyan
 * @date 2020/11/25 0:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "gmall.thread")
public class ThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
