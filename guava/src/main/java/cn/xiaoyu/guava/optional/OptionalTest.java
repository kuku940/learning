package cn.xiaoyu.guava.optional;

import com.google.common.base.Optional;

/**
 * Optional用于包含非空对象的不可变对象，用于不存在值表示为null
 * 这个类有各种使用的方法，以方便来处理为可用或不可用，而不是检查null值
 * <p>
 * 和Java8的Optional相似
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer v1 = null;
        Integer v2 = new Integer(10);

        Optional<Integer> a = Optional.fromNullable(v1);
        Optional<Integer> b = Optional.of(v2);

        System.out.println(sum(a, b));
    }

    private static int sum(Optional<Integer> a, Optional<Integer> b) {
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }
}
