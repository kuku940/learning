package cn.xiaoyu.learning.netty.messagepack;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * Created by roin_zhang on 2016/10/10.
 */
@Message
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;

    public UserInfo(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public UserInfo() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
