package com.gxyan.gmall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gxyan
 * @date 2020/12/5 19:51
 */
@Data
@ConfigurationProperties(prefix = "gmall.thread")
public class ThreadPoolConfigProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
}
