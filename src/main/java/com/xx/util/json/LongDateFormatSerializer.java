package com.xx.util.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by jishu04 on 2016/10/25.
 */
public class LongDateFormatSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if(object == null) {
            serializer.getWriter().writeNull();
        } else {
            Date date = (Date)object;
            serializer.write(date.getTime() + "");
        }
    }
}
