package cn.xiaoyu.java.annotation.domain;

import cn.xiaoyu.java.annotation.component.Person;

/**
 * 图书信息
 */
public class Book {
    private String name;
    private Person person;

    @Person(name = "zhaoxiaoxiao")
    public void setPerson() {
        System.out.println("hello world!");
    }
}
