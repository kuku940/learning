package cn.xiaoyu.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

interface Class2Mocked {
    String say(String name);

    void show();
}

/**
 * @author Roin zhang
 * @date 2018/7/25
 */

public class TestMockito {
    /**
     * Mockito第一个例子
     */
    @Test
    public void testMockitoMethod() {
        // 使用mockito生成Mock对象
        Class2Mocked obj = Mockito.mock(Class2Mocked.class);

        // 定义Mock对象的行为和输出
        Mockito.when(obj.say("Jack")).thenReturn("hello Jack");
        // 调用Mock对象进行单元测试
        Assert.assertEquals("hello Jack", obj.say("Jack"));
        Assert.assertNotEquals("hello Mary", obj.say("Jack"));

        // 对Mock对象的行为进行验证
//        Mockito.verify(obj).say("Jack");
        Mockito.verify(obj, Mockito.times(2)).say("Jack");
    }

    /**
     * 验证顺序
     */
    @Test
    public void testMockitoMethod1() {
        Class2Mocked obj1 = Mockito.mock(Class2Mocked.class);
        Class2Mocked obj2 = Mockito.mock(Class2Mocked.class);

        Mockito.when(obj1.say("Jack")).thenReturn("hello Jack");
        Mockito.when(obj2.say("Jack")).thenReturn("hi Jack");

        Assert.assertEquals("hello Jack", obj1.say("Jack"));
        Assert.assertEquals("hi Jack", obj2.say("Jack"));

        // //此行并不决定顺序，下面的两行才开始验证顺序
        InOrder inOrder = Mockito.inOrder(obj2, obj1);
        inOrder.verify(obj1).say("Jack");
        inOrder.verify(obj2).say("Jack");
    }

    /**
     * 非局部模拟
     */
    @Test
    public void testMockitoMethod2() {
        Class2Mocked obj = Mockito.mock(Class2Mocked.class);

        Assert.assertNull(obj.say("Jack"));
        obj.show();

        Mockito.verify(obj).say("Jack");
        Mockito.verify(obj).show();
    }

    /**
     * 局部模拟 - doCallRealMethod()方式
     * 执行say("Jack")时会执行原有的代码，而执行say("Ma")时则是返回默认值null且没有输出打印，执行show()同样没有输出打印。
     */
    @Test
    public void testMockitoMethod3() {
        Class3Method obj = Mockito.mock(Class3Method.class);

        Mockito.doCallRealMethod().when(obj).say("Jack");
        Assert.assertEquals("hello Jack", obj.say("Jack"));
        Assert.assertNull(obj.say("Ma"));
        obj.show();

        Mockito.verify(obj).say("Jack");
        Mockito.verify(obj).say("Ma");
        Mockito.verify(obj).show();
    }

    /**
     * 局部模拟 - spy()方式
     * 运行这个测试会发现在执行hello("z3")时会执行原有的代码，但是执行show()时在控制台中没有打印语句
     */
    @Test
    public void testMockitoMethod4() {
        Class3Method obj = Mockito.spy(Class3Method.class);

        Mockito.doNothing().when(obj).show();
        Assert.assertEquals("hello Jack", obj.say("Jack"));
        obj.show();

        Mockito.verify(obj).say("Jack");
        Mockito.verify(obj).show();
    }
}

class Class3Method implements Class2Mocked {

    @Override
    public String say(String name) {
        System.out.println("hello " + name);
        return "hello " + name;
    }

    @Override
    public void show() {
        System.out.println("hello world!");
    }
}
