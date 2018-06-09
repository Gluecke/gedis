package com.garasha.gedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class GedisAppConfig {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        final RedisConfig.Redis redis = redisConfig.getRedis();
        try {
            final URI uri = new URI(System.getenv("REDIS_URL"));
            final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(uri.getHost(), uri.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Uri syntax of redis host");
        }

    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
