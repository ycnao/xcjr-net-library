package com.xcjr.lib.net.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * fastjson工具类。
 */
public class FastJsonUtil {

    private FastJsonUtil() {

    }

    /**
     * 将jsonObject转换成java对象。
     *
     * @param jsonStr JsonObject
     * @param class1  <T>
     */
    public static <T> T parseToJavaObject(String jsonStr, Class<T> class1) {
        if (jsonStr == null || jsonStr.isEmpty()) {
//			throw new NullPointerException();
            jsonStr = "{}";
        }
        return JSON.parseObject(filterNullStrAndReturnStr(jsonStr), class1);
    }

    /**
     * @param jsonObject Object
     * @param class1     <T>
     */
    public static <T> T parseToJavaObject(Object jsonObject, Class<T> class1) {
        if (jsonObject == null) {
//			throw new NullPointerException();
            jsonObject = new JSONObject();
        }
        String jsonStr = JSON.toJSONString(jsonObject);
        return JSON.parseObject(filterNullStrAndReturnStr(jsonStr), class1);
    }


    /**
     * 过滤掉jsonObject中字符串value为null的值，用空代替。
     *
     * @param jsonStr 字符串
     * @return 返回json对象
     */
    public static JSONObject filterNullStrAndReturnJson(String jsonStr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Set<String> keySet = jsonObject.keySet();
        for (String key : keySet) {
            Object object = jsonObject.get(key);
            if (object == null) {
                jsonObject.put(key, "");
            } else if (object instanceof String) {
                String str = (String) object;
                if (str == null || str.isEmpty() || str.equalsIgnoreCase("null")) {
                    jsonObject.put(key, "");
                }
            }
        }
        return jsonObject;
    }

    /**
     * 过滤掉jsonObject中字符串value为null的值，用空代替。
     *
     * @param jsonStr 字符串
     * @return 返回json对象字符串
     */
    public static String filterNullStrAndReturnStr(String jsonStr) {
        return JSONObject.toJSONString(filterNullStrAndReturnJson(jsonStr));
    }
}
