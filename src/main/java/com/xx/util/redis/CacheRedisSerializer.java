package com.xx.util.redis;

import com.alibaba.fastjson.JSON;
import com.xx.constant.Charsets;
import com.xx.util.thread.ThreadLocalContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by jishu04 on 2016/8/29.
 */
public class CacheRedisSerializer implements RedisSerializer<Object> {

    static public final String javaTypeKey = "CacheRedisSerializer_JavaType";

    @Override
    public byte[] serialize(Object s) throws SerializationException {
        if (s == null) {
            return new byte[0];
        }
        if (s instanceof String) {
            return ((String) s).getBytes(Charsets.UTF8);
        }
        return JSON.toJSONString(s).getBytes(Charsets.UTF8);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        Object javaTypeObj = ThreadLocalContext.get(javaTypeKey);
        Class<?> javaType = null;
        ParameterizedType parameterizedType = null;
        if (javaTypeObj instanceof Class) {
            javaType = (Class<?>) javaTypeObj;
        } else if (javaTypeObj instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType) javaTypeObj;
        }

        if (bytes != null && bytes.length > 0) {
            if (javaType != null) {
                if (javaType.equals(String.class)) {
                    return new String(bytes, Charsets.UTF8);
                } else {
                    return JSON.parseObject(bytes, javaType);
                }
            } else if (parameterizedType != null) {
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                    return JSON.parseArray(new String(bytes, Charsets.UTF8), (Class<?>) actualTypeArguments[0]);
                }
            }
            return JSON.parseObject(bytes, Object.class);
        } else {
            return null;
        }
    }
}
