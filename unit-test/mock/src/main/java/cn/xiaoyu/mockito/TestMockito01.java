package cn.xiaoyu.mockito;

import cn.xiaoyu.mockito.entity.ITool;
import cn.xiaoyu.mockito.entity.ToolImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Roin zhang
 * @date 2018/7/25
 */
public class TestMockito01 {
    /**
     * Mockito第一个例子
     */
    @Test
    public void testMockitoMethod() {
        // 使用mockito生成Mock对象
        ITool obj = mock(ITool.class);

        // 定义Mock对象的行为和输出
        when(obj.say("Jack")).thenReturn("hello Jack", "hello world");
        // 调用Mock对象进行单元测试
        Assert.assertEquals("hello Jack", obj.say("Jack"));
        Assert.assertNotEquals("hello Mary", obj.say("Jack"));

        // 对Mock对象的行为进行验证
//        verify(obj).say("Jack");
        verify(obj, times(2)).say("Jack");
    }

    /**
     * 验证顺序
     */
    @Test
    public void testMockitoMethod1() {
        ITool obj1 = mock(ITool.class);
        ITool obj2 = mock(ITool.class);

        when(obj1.say("Jack")).thenReturn("hello Jack");
        when(obj2.say("Jack")).thenReturn("hi Jack");

        Assert.assertEquals("hello Jack", obj1.say("Jack"));
        Assert.assertEquals("hi Jack", obj2.say("Jack"));

        // 此行并不决定顺序，下面的两行才开始验证顺序
        InOrder inOrder = inOrder(obj2, obj1);
        inOrder.verify(obj1).say("Jack");
        inOrder.verify(obj2).say("Jack");
    }

    /**
     * 非局部模拟
     */
    @Test
    public void testMockitoMethod2() {
        ITool obj = mock(ITool.class);

        Assert.assertNull(obj.say("Jack"));
        obj.show();

        verify(obj).say("Jack");
        verify(obj).show();
    }

    /**
     * 局部模拟 - doCallRealMethod()方式
     * 执行say("Jack")时会执行原有的代码，而执行say("Ma")时则是返回默认值null且没有输出打印，执行show()同样没有输出打印。
     */
    @Test
    public void testMockitoMethod3() {
        ToolImpl obj = mock(ToolImpl.class);

        doCallRealMethod().when(obj).say("Jack");
        Assert.assertEquals("hello Jack", obj.say("Jack"));
        Assert.assertNull(obj.say("Ma"));
        obj.show();

        verify(obj).say("Jack");
        verify(obj).say("Ma");
        verify(obj).show();
    }

    /**
     * 局部模拟 - spy()方式
     * 运行这个测试会发现在执行hello("z3")时会执行原有的代码，但是执行show()时在控制台中没有打印语句
     */
    @Test
    public void testMockitoMethod4() {
        ToolImpl obj = spy(ToolImpl.class);

        doNothing().when(obj).show();
        Assert.assertEquals("hello Jack", obj.say("Jack"));
        obj.show();

        verify(obj).say("Jack");
        verify(obj).show();
    }
}

