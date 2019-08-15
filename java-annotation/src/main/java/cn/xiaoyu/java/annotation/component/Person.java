package cn.xiaoyu.java.annotation.component;

import cn.xiaoyu.java.annotation.enums.GenderEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Person {
    String name() default "xiaoxiao";
    GenderEnum gender() default GenderEnum.Female;
}