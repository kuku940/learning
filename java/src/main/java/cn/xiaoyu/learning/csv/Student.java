package cn.xiaoyu.learning.csv;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 01399268
 * @date 2020/9/27
 */
@Data
public class Student {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 分数
     */
    private Double score;
    /**
     * 生日
     */
    private Date birth;
    /**
     * 入学时间
     */
    private Calendar admission;
}
