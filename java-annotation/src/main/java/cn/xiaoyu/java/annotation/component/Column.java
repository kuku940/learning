package cn.xiaoyu.java.annotation.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Column注解
 * 作用域：@Target(ElementType.FIELD) - 字段
 * 生命周期：@Retention(RetentionPolicy.RUNTIME)
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value();
}
