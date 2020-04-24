package cn.xiaoyu.jmockit.common;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

/**
 * Mock一个类
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class ClassMockingTest {
    @Test
    public void testClassMockingByExpectation() {
        AnOrdinaryClass instanceRecord = new AnOrdinaryClass();
        new Expectations(AnOrdinaryClass.class) {{
            // mock静态方法
            AnOrdinaryClass.staticMethod();
            result = 10;

            // mock普通方法
            instanceRecord.ordinaryMethod();
            result = 20;

            // mock final方法
            instanceRecord.finalMethod();
            result = 30;

            // native, private方法无法使用Expectations来Mock
        }};

        AnOrdinaryClass instance = new AnOrdinaryClass();
        Assert.assertEquals(10, AnOrdinaryClass.staticMethod());
        Assert.assertEquals(20, instanceRecord.ordinaryMethod());
        Assert.assertEquals(30, instanceRecord.finalMethod());
//        Assert.assertEquals(4, instance.navtiveMethod()); // 无原生方法的dll文件/so文件
        Assert.assertEquals(5, instance.callPrivateMethod());
    }

    @Test
    public void testClassMockingByMockUp() {
        new AnOrdinaryClassMockUp();
        AnOrdinaryClass instance = new AnOrdinaryClass();
        Assert.assertTrue(AnOrdinaryClass.staticMethod() == 10);
        Assert.assertTrue(instance.ordinaryMethod() == 20);
        Assert.assertTrue(instance.finalMethod() == 30);
        Assert.assertTrue(instance.navtiveMethod() == 40);
        Assert.assertTrue(instance.callPrivateMethod() == 50);
    }

    /**
     * AnOrdinaryClass的MockUp类，继承MockUp即可
     */
    public static class AnOrdinaryClassMockUp extends MockUp<AnOrdinaryClass> {
        @Mock
        public static int staticMethod() {
            return 10;
        }

        @Mock
        public int ordinaryMethod() {
            return 20;
        }

        @Mock
        public final int finalMethod() {
            return 30;
        }

        @Mock
        public int navtiveMethod() {
            return 40;
        }

        @Mock
        private int privateMethod() {
            return 50;
        }
    }
}

