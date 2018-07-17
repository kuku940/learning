package cn.xiaoyu.guava.primitives;

import com.google.common.primitives.Doubles;

import java.util.List;

/**
 * Doubles是double基本类型的实用工具类
 */

public class DoublesTest {
    public static void main(String[] args) {
        double[] doubleArrays = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};

        //convert array of primitives to array of objects
        List<Double> objectArray = Doubles.asList(doubleArrays);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        doubleArrays = Doubles.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < doubleArrays.length; i++) {
            System.out.print(doubleArrays[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("5.0 is in list? " + Doubles.contains(doubleArrays, 5.0f));

        //return the index of element
        System.out.println("5.0 position in list " + Doubles.indexOf(doubleArrays, 5.0f));

        //Returns the minimum
        System.out.println("Min: " + Doubles.min(doubleArrays));

        //Returns the maximum
        System.out.println("Max: " + Doubles.max(doubleArrays));
    }
}
