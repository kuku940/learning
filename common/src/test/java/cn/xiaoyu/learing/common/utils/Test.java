package cn.xiaoyu.learing.common.utils;

import cn.xiaoyu.learing.common.domain.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Roin zhang
 * @date 2018/8/24
 */

public class Test {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("hah", 18));
        list.add(new Person("haha", 16));
        list.add(new Person("hehe", 19));

        list.forEach(System.out::println);
        list.sort(Comparator.comparingInt(Person::getAge));
        list.forEach(System.out::println);
    }
}
