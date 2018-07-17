package cn.xiaoyu.learning.cxfservice.domain;

import java.util.Arrays;

public class User {
    private long id;
    private String username;
    private int age;
    private String[] habby;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getHabby() {
        return habby;
    }

    public void setHabby(String[] habby) {
        this.habby = habby;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User() {
    }

    public User(long id, String username, int age, String[] habby) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.habby = habby;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", habby=" + Arrays.toString(habby) +
                '}';
    }
}
