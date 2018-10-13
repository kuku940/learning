package cn.xiaoyu.jmockit.senior;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Before;
import org.junit.Test;

/**
 * 使用Mockup来mock静态方法和私有方法
 *
 * @author Roin zhang
 * @date 2018/8/28
 */

public class PrivateMockUpTest {
    @Before
    public void setup() {
        new MockUp<Person>(Person.class) {
            @Mock
            public String run() {
                return "Running!";
            }

            @Mock
            public String name() {
                return "Hello Tom!";
            }
        };
    }

    @Test
    public void test() {
        Person per = new Person();
        System.out.println(Person.run());
        System.out.println(per.say());
    }
}

class Person {
    public static String run() {
        return "Run Slowly";
    }

    private String name() {
        return "my name is Tom";
    }

    public String say() {
        return name();
    }
}