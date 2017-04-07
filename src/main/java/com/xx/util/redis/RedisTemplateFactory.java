package com.xx.util.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by jishu04 on 2016/8/26.
 */
public class RedisTemplateFactory {

    private RedisConnectionFactory redisConnectionFactory;

    public RedisTemplateFactory(RedisConnectionFactory factory) {
        redisConnectionFactory = factory;
    }

    public <T> RedisTemplate<String, T> create(Class<T> a) {
        RedisTemplate<String, T> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        MyRedisSerializer myRedisSerializer = new MyRedisSerializer(a);
        template.setValueSerializer(myRedisSerializer);
        template.setHashValueSerializer(myRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }
}
