package cn.xiaoyu.learning.im.serialize.impl;

import cn.xiaoyu.learning.im.serialize.Serializer;
import cn.xiaoyu.learning.im.serialize.SerializerAlgorithm;
import com.alibaba.fastjson.JSON;

/**
 * Json方式序列化
 *
 * @author roin.zhang
 * @date 2019/9/24
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
