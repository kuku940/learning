package cn.xiaoyu.guava.primitives;

import com.google.common.primitives.Shorts;

import java.util.List;

/**
 * Shorts是基本类型short的实用工具类
 */

public class ShortsTest {
    public static void main(String[] args) {
        short[] shortArrays = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Short> objectArray = Shorts.asList(shortArrays);
        System.out.println(objectArray.toString());

        shortArrays = Shorts.toArray(objectArray);
        System.out.print("[");
        System.out.print("[ ");
        for(int i = 0; i< shortArrays.length ; i++){
            System.out.print(shortArrays[i] + " ");
        }
        System.out.println("]");

        short data = 5;
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? "+ Shorts.contains(shortArrays, data));

        //Returns the minimum
        System.out.println("Min: " + Shorts.min(shortArrays));

        //Returns the maximum
        System.out.println("Max: " + Shorts.max(shortArrays));
        data = 2400;
        //get the byte array from an integer
        byte[] byteArray = Shorts.toByteArray(data);
        for(int i = 0; i< byteArray.length ; i++){
            System.out.print(byteArray[i] + " ");
        }
    }
}
