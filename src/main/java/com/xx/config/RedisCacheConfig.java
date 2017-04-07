package com.xx.config;

import com.xx.util.redis.CacheRedisSerializer;
import com.xx.util.redis.MyRedisSerializer;
import com.xx.util.spring.CacheKey;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;

/**
 * Created by jishu04 on 2016/8/8.
 */
@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
@Import(RedisConfig.class)
public class RedisCacheConfig extends CachingConfigurerSupport {

    private boolean usePrefix = true;

    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {

                StringBuilder sb = new StringBuilder();

                Class<?> cls = target.getClass();

                if (!usePrefix) {
                    Class<?> superCls = cls.getSuperclass();

                    if (superCls != null) {
                        CacheConfig superCacheConfig = superCls.getAnnotation(CacheConfig.class);

                        if (superCacheConfig != null && superCacheConfig.cacheNames() != null && superCacheConfig.cacheNames().length > 0 && !superCacheConfig.cacheNames()[0].isEmpty()) {
                            sb.append(superCacheConfig.cacheNames()[0]);
                            sb.append(":");
                        }
                    }
                }

                CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);

                if (cacheConfig != null && cacheConfig.cacheNames() != null && cacheConfig.cacheNames().length > 0 && !cacheConfig.cacheNames()[0].isEmpty()) {
                    sb.append(cacheConfig.cacheNames()[0]);
                } else {
                    sb.append(cls.getName());
                }

                if (params.length > 0) {
                    sb.append(":");

                    for (Object obj : params) {
                        if (obj == null) {
                            sb.append("NULL");
                        } else if (obj instanceof CacheKey) {
                            sb.append(((CacheKey) obj).getKey());
                        } else {
                            sb.append(obj.toString());
                        }
                        sb.append("_");
                    }

                    sb.deleteCharAt(sb.length() - 1);
                }

                return sb.toString();
            }
        };
    }

    @Bean
    public RedisTemplate cacheRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        MyRedisSerializer stringRedisSerializer = new MyRedisSerializer(String.class);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        CacheRedisSerializer cacheRedisSerializer = new CacheRedisSerializer();
        redisTemplate.setValueSerializer(cacheRedisSerializer);
        redisTemplate.setHashValueSerializer(cacheRedisSerializer);

        return redisTemplate;
    }

//    @Bean
    public CacheManager cacheManager(RedisTemplate cacheRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(cacheRedisTemplate);
        cacheManager.setUsePrefix(usePrefix);
        cacheManager.setDefaultExpiration(0);
        return cacheManager;
    }
}
