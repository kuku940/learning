package cn.xiaoyu.learning.im.serialize;

import cn.xiaoyu.learning.im.serialize.impl.JSONSerializer;

/**
 * @author roin.zhang
 * @date 2019/9/24
 */
public interface Serializer {
    /**
     * Json序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * Java对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二级制转换成Java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
