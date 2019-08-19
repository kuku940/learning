package cn.xiaoyu.learning.common.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author roin.zhang
 * @date 2019-08-19
 * <p>
 * 策略枚举类型，不同的协议类型使用不同的序列化方式
 */

public enum SerializerUtils {
    FASTJSON() {
        @Override
        public <T> String serialize(T t, String encoding) {
            return JSON.toJSONString(t, SerializerFeature.WriteNullStringAsEmpty);
        }

        @Override
        public <T> T deserialize(String json, Class<T> tClass) {
            return JSON.parseObject(json, tClass);
        }
    },

    JACKSON() {
        @Override
        public <T> String serialize(T t, String encoding) {
            return JacksonUtils.toJson(t);
        }

        @Override
        public <T> T deserialize(String json, Class<T> tClass) {
            return JacksonUtils.fromJson(json, tClass);
        }
    };

    /**
     * 工厂方法
     */
    public static SerializerUtils of(Type sf) {
        return SerializerUtils.valueOf(sf.name());
    }

    /**
     * 序列化对象
     */
    public abstract <T> String serialize(T t, String encoding);

    /**
     * 反序列化对象
     */
    public abstract <T> T deserialize(String text, Class<T> tClass);
}
