package cn.xiaoyu.java.annotation.domain;

import cn.xiaoyu.java.annotation.component.Column;
import cn.xiaoyu.java.annotation.component.Table;

/**
 * User实体类
 */
@Table("user")
public class User {
    @Column("id")
    private long id;
    @Column("user_name")
    private String username;
    private String gender;
    private int age;

    public User() {
    }

    public User(long id, String username, String gender, int age) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
