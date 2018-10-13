package cn.xiaoyu.learning.common.utils;

import cn.xiaoyu.learning.common.exception.BizException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Roin zhang
 * @date 2018/8/6
 */

public class DeepCopyUtils {
    /**
     * 通过序列化来完成深度复制
     *
     * @param from 需要复制的对象
     * @return 复制后的对象
     */
    public static Object deepCopy(Object from) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(bos)) {

            out.writeObject(from);
            out.flush();

            // 从流中读出 byte array，调用readObject函数反序列化出对象
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new BizException(e.getMessage(), e);
        }
    }

}
