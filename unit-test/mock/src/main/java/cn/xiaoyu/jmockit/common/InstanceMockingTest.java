package cn.xiaoyu.jmockit.common;

import mockit.Deencapsulation;
import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;

/**
 * 使用Mock来Mock一个实例
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class InstanceMockingTest {
    @Test
    public void testInstanceMockingByExpectation() {
        AnOrdinaryClass instanceRecord = new AnOrdinaryClass();

        // 设置私有成员变量的值
        Deencapsulation.setField(instanceRecord, "value", 5);

        // 直接把实例传给Expectations的构造函数即可Mock这个实例
        new Expectations(instanceRecord) {{
            // 尽管这里也可以Mock静态方法，但不推荐在这里写。静态方法的Mock应该是针对类的

            // mock普通方法
            instanceRecord.ordinaryMethod();
            result = 20;
            // mock final方法
            instanceRecord.finalMethod();
            result = 30;
            // native, private方法无法用Expectations来Mock
        }};

        Assert.assertEquals(5, instanceRecord.getValue());
        Assert.assertEquals(1, AnOrdinaryClass.staticMethod());
        Assert.assertEquals(20, instanceRecord.ordinaryMethod());
        Assert.assertEquals(30, instanceRecord.finalMethod());
//        Assert.assertEquals(4, instance.navtiveMethod()); // 无原生方法的dll文件/so文件
        Assert.assertEquals(5, instanceRecord.callPrivateMethod());
    }

}
