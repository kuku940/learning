package cn.xiaoyu.learning.kafka.stream;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * 反序列化工具
 *
 * @param <T>
 */
public class JsonDeserializer<T> implements Deserializer<T> {
    private Gson gson = new Gson();
    private Class<T> clazz;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        if (clazz == null) {
            clazz = (Class<T>) configs.get("serializedClass");
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        return gson.fromJson(new String(data), clazz);
    }

    @Override
    public void close() {

    }

    public JsonDeserializer() {

    }

    public JsonDeserializer(Class clazz) {
        this.clazz = clazz;
    }
}
