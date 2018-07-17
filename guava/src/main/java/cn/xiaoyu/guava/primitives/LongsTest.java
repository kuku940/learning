package cn.xiaoyu.guava.primitives;

import com.google.common.primitives.Longs;

import java.util.List;

/**
 * Longs是基本类型long的实用工具类
 */

public class LongsTest {
    public static void main(String[] args) {
        long[] longArrays = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //convert array of primitives to array of objects
        List<Long> objectArray = Longs.asList(longArrays);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        longArrays = Longs.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < longArrays.length; i++) {
            System.out.print(longArrays[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Longs.contains(longArrays, 5));

        //Returns the minimum
        System.out.println("Min: " + Longs.min(longArrays));

        //Returns the maximum
        System.out.println("Max: " + Longs.max(longArrays));

        //get the byte array from an integer
        byte[] byteArray = Longs.toByteArray(20000);
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
    }
}
