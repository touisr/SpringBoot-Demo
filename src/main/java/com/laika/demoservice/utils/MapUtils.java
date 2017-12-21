package com.laika.demoservice.utils;

import java.util.Map;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/1/13 11:21
 * @description: 集合工具类
 */
public class MapUtils {


    /**
     * 从map中获取指定key的值，如不存在，则返回默认值（jdk8中已经有此方法）
     *
     * @param params
     * @param key
     * @param defauleVal
     * @return
     */
    public static String getOrDefault(Map<String, Object> params, String key, String defauleVal) {

        return params.get(key) == null ? defauleVal : params.get(key).toString();
    }
}