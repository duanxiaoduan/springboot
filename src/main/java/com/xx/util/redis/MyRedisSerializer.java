package com.xx.util.redis;

import com.alibaba.fastjson.JSON;
import com.xx.constant.Charsets;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by jishu04 on 2016/8/28.
 */
public class MyRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> javaType;

    public MyRedisSerializer(Class<T> type) {
        this.javaType = type;
    }

    @Override
    public byte[] serialize(T s) throws SerializationException {
        if (s == null) {
            return new byte[0];
        }
        if (s instanceof String) {
            return ((String) s).getBytes(Charsets.UTF8);
        }
        return JSON.toJSONString(s).getBytes(Charsets.UTF8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null && bytes != null && bytes.length > 0) {
            if (javaType.equals(String.class)) {
                return ((T) new String(bytes, Charsets.UTF8));
            } else{
                return JSON.parseObject(bytes, javaType);
            }
        } else {
            return null;
        }
    }
}