package cn.xiaoyu.learning.java8.optional;

import java.util.Optional;

/**
 * Optional - null的容器对象
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer v1 = null;
        Integer v2 = new Integer(10);

        Optional<Integer> a = Optional.ofNullable(v1);
        Optional<Integer> b = Optional.of(v2);

        System.out.println(sum(a, b));
    }

    private static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}
