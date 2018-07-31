package com.rabbitmq.rabbitmqdemo.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.rabbitmq.rabbitmqdemo.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FastJsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(FastJsonUtils.class);
    
    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.DisableCircularReferenceDetect //禁止循环引用检测
    };

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
    
    // 转换为List
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
    
    public static <T> T jsonToBeanFromNode(String jsonString, String fieldName, Class<T> clazz) {
        String json = findValue(jsonString, fieldName).toString();
        return FastJsonUtils.toBean(json, clazz);
    }
    /***
     * 根据节点来查找
     * 
     * @param json
     * @param fieldName
     * @return
     */
    public static JsonNode findValue(String json, String fieldName) {
        if (StringUtils.isEmpty(json)) {
            return NullNode.instance;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(json);
            rootNode = rootNode.findValue(fieldName);
            if (null == rootNode) {
                return NullNode.instance;
            }
            return rootNode;
        } catch (Exception e) {
            logger.error("读取json:{}的fieldName:{}节点失败,异常信息：{}", json, fieldName, e);
            return NullNode.instance;
        }
    }

    /**
     * json字符串转化为map
     *
     * @param s
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }
    /**
     * jsonArray转换成List
     *
     * @param jsonArray
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArrayToList(JSONArray jsonArray, Class<T> clazz) {
        String json = JSON.toJSONString(jsonArray);
        return JSON.parseArray(json, clazz);
    }

    /**
     * 生产简单jsonObject
     *
     * @param key
     * @param object
     * @return
     */

    public static JSONObject putParmToJsonObject(String key, Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, object);
        return jsonObject;
    }
    
    public static boolean isResultSuccess(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getIntValue(Constants.CODE_FLAG) == Constants.SUCCESS_CODE;
    }
    
    public static boolean isResultError(String json) {
        return !isResultSuccess(json);
    }

    public static boolean isResultError(JSONObject responseObject) {
        return Objects.isNull(responseObject) || responseObject.getIntValue(Constants.CODE_FLAG) != Constants.SUCCESS_CODE;
    }
}
