package com.garasha.gedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class GedisAppConfig {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        final RedisConfig.Redis redis = redisConfig.getRedis();
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redis.getHost(), redis.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
