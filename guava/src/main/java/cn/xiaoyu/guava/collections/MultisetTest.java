package cn.xiaoyu.guava.collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * 扩展设置有重复接口
 */

public class MultisetTest {
    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.addAll(Arrays.asList("a", "b", "c", "d", "e", "d", "d", "a"));

        System.out.println("Occurrence of 'd':" + multiset.count("d"));
        System.out.println("Total Size:" + multiset.size());

        Set<String> set = multiset.elementSet();
        System.out.print("Set [");
        set.forEach(s -> System.out.print(s + ","));
        System.out.print("]");

        Iterator<String> iterator = multiset.iterator();
        System.out.print("Multiset [");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println("]");

        System.out.println("Multiset [");
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("Element: " + entry.getElement() + ", Occurrence(s):" + entry.getCount());
        }
        System.out.println("]");
    }
}
