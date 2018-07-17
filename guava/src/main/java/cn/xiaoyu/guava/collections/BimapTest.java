package cn.xiaoyu.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * 特殊映射，没有重复值
 */

public class BimapTest {
    public static void main(String[] args) {
        BiMap<Integer, String> empIdNameMap = HashBiMap.create();

        empIdNameMap.put(new Integer(101), "Jack");
        empIdNameMap.put(new Integer(102), "Pony");
        empIdNameMap.put(new Integer(103), "Robin");

        System.out.println(empIdNameMap.get(101));
        System.out.println(empIdNameMap.inverse().get("Jack"));
    }
}
