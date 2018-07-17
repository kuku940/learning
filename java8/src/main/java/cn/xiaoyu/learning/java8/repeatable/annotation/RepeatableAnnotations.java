package cn.xiaoyu.learning.java8.repeatable.annotation;

import java.lang.annotation.*;

/**
 * 重复注解
 * Java8引入了重复注解的概念，允许在同一个地方多次使用同一个注解。
 */
public class RepeatableAnnotations {
    public static void main(String[] args) {
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    @Filter("0001")
    @Filter("0002")
    public interface Filterable {

    }
}
