package com.xx.util;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * Created by jishu04 on 2016/8/15.
 */
public class Splitters {

    static public final Splitter defaultSpitter = Splitter.on(",");

    static public List<String> splitToList(String str) {
        return defaultSpitter.splitToList(str);
    }

    public static Iterable<String> split(String str) {
        return defaultSpitter.split(str);
    }
}
