package cn.xiaoyu.guava.primitives;

import com.google.common.primitives.Chars;

import java.util.List;

/**
 * Chars是基本char类型的实用工具类
 */

public class CharsTest {
    public static void main(String[] args) {
        char[] charArrays = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        //convert array of primitives to array of objects
        List<Character> objectArray = Chars.asList(charArrays);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        charArrays = Chars.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < charArrays.length; i++) {
            System.out.print(charArrays[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("c is in list? " + Chars.contains(charArrays, 'c'));

        //return the index of element
        System.out.println("c position in list " + Chars.indexOf(charArrays, 'c'));

        //Returns the minimum
        System.out.println("Min: " + Chars.min(charArrays));

        //Returns the maximum
        System.out.println("Max: " + Chars.max(charArrays));
    }
}
