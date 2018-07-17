package cn.xiaoyu.java.annotation.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Table注解
 * 作用域：@Target(ElementType.TYPE)
 * 生命周期：@Retention(RetentionPolicy.RUNTIME)
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
