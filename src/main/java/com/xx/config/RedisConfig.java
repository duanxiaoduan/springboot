package com.xx.config;

import com.xx.util.redis.RedisTemplateFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by jishu04 on 2016/7/29.
 */
@Configuration
public class RedisConfig implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        propertyResolver = new RelaxedPropertyResolver(env, "spring.redis.");
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(propertyResolver.getProperty("host"));
        factory.setPort(propertyResolver.getProperty("port", int.class, 6379));
        factory.setTimeout(propertyResolver.getProperty("timeout", int.class, 0));
        factory.setPassword(propertyResolver.getProperty("password", ""));

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(propertyResolver.getProperty("max-active", int.class, 200));
        poolConfig.setMaxIdle(propertyResolver.getProperty("max-idle", int.class, -1));
        poolConfig.setMaxWaitMillis(propertyResolver.getProperty("max-wait", int.class, -1));
        poolConfig.setMinIdle(propertyResolver.getProperty("min-idle", int.class, 0));

        factory.setPoolConfig(poolConfig);

        return factory;
    }

    @Bean
    public RedisTemplateFactory redisTemplateFactory(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTemplateFactory(redisConnectionFactory);
    }
}
