package cn.xiaoyu.jmockit.senior;

import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Mock方法中调用老方法
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class InvocationMockUpTest {
    @Test
    public void testMockUp() {
        // 对Java自带类Calendar的get方法进行定制
        new MockUp<Calendar>(Calendar.class) {
            // 申明参数invocation，表示老方法的调用
            @Mock
            public int get(Invocation invocation, int unit) {
                // 只希望时间是早上7点
                if (unit == Calendar.HOUR_OF_DAY) {
                    return 7;
                }
                // 其它时间（年份，月份，日，分，秒均不变)
                return invocation.proceed(unit);
            }
        };
        Calendar now = Calendar.getInstance();
        // 只有小时变成Mock方法
        Assert.assertEquals(7, now.get(Calendar.HOUR_OF_DAY));
        // 其它的还是走老的方法
        Assert.assertEquals((new Date()).getMonth(), now.get(Calendar.MONTH));
        Assert.assertEquals((new Date()).getDate(), now.get(Calendar.DAY_OF_MONTH));
    }
}