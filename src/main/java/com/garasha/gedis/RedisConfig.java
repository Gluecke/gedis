package com.garasha.gedis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class RedisConfig {
    private Redis redis;
    @Data
    public static class Redis {
        private String host;
        private Integer port;
    }
}
