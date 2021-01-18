package cn.xiaoyu.learning.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置为中国上海时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

        // 解析支持没有引号的情况
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 解析器支持解析单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 解析器支持解析结束符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 设置Date类型对象的转换格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    private JsonUtils() {
    }

    /**
     * Object对象转换成Json字符串
     */
    public static String writeObject2JSON(Object obj)
            throws JsonProcessingException {
        // writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Json字符串转换成Object对象
     */
    public static Object writeJSON2Object(String json, Class obj) throws IOException {
        return objectMapper.readValue(json, obj);
    }

    /**
     * json字符串转对象
     */
    public static <T> T writeJSON2Entity(String json, Class<T> obj) throws IOException {
        return objectMapper.readValue(json, obj);
    }

    /**
     * JSON字符串转LIST或者Map
     */
    public static Object writeJSON2Collection(String json, Class<?> collectionClass, Class<?>... elementClasses) throws IOException {
        JavaType jt = objectMapper.getTypeFactory()
                .constructParametricType(collectionClass, elementClasses);
        return objectMapper.readValue(json, jt);
    }
}
