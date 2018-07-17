package cn.xiaoyu.guava.primitives;

import com.google.common.primitives.Bytes;

import java.util.List;

/**
 * Bytes是bytes的基本类型的实用工具
 */

public class BytesTest {
    public static void main(String[] args) {
        byte[] byteArrays = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Byte> objectArray = Bytes.asList(byteArrays);

        byteArrays = Bytes.toArray(objectArray);
        System.out.print("[");
        for (int i = 0; i < byteArrays.length; i++) {
            System.out.print(byteArrays[i] + " ");
        }
        System.out.println("]");

        byte data = 5;
        System.out.println("5 is in list? " + Bytes.contains(byteArrays, data));
        System.out.println("Index of 5: " + Bytes.indexOf(byteArrays, data));
        System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArrays, data));
    }
}
