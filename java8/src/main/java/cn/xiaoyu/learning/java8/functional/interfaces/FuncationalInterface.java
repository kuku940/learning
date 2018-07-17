package cn.xiaoyu.learning.java8.functional.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 函数式接口 Functional Interface
 */
public class FuncationalInterface {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        // 输出所有的元素
        System.out.print("输出所有的元素：");
        eval(list, n -> true);

        // 输出所有的偶数
        System.out.print("输出所有的偶数：");
        eval(list, n -> n % 2 == 0);

        // 输出所有大于3的元素
        System.out.print("输出所有大于3的元素：");
        eval(list, n -> n > 3);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer i : list) {
            if (predicate.test(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
