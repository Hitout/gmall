package com.gxyan.gmall.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gxyan
 * @date 2020/12/6 21:59
 */
@Data
@ConfigurationProperties(prefix = "gmall.thread")
public class ThreadPoolConfigProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
}
