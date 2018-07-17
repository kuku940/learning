package cn.xiaoyu.guava.primitives;

import com.google.common.primitives.Floats;

import java.util.List;

/**
 * Floats是float基本类型的实用工具类
 */

public class FloatsTest {
    public static void main(String[] args) {
        float[] floatArray = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f};

        //convert array of primitives to array of objects
        List<Float> objectArrays = Floats.asList(floatArray);
        System.out.println(objectArrays.toString());

        //convert array of objects to array of primitives
        floatArray = Floats.toArray(objectArrays);
        System.out.print("[ ");
        for (int i = 0; i < floatArray.length; i++) {
            System.out.print(floatArray[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("5.0 is in list? " + Floats.contains(floatArray, 5.0f));

        //return the index of element
        System.out.println("5.0 position in list " + Floats.indexOf(floatArray, 5.0f));

        //Returns the minimum
        System.out.println("Min: " + Floats.min(floatArray));

        //Returns the maximum
        System.out.println("Max: " + Floats.max(floatArray));
    }
}
