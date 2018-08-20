package cn.xiaoyu.jmockit.base;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

/**
 * MockUp和@Mock
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class JMockit005MockUpTest {
    @Test
    public void testMockUp() {
        new MockUp<Calendar>(Calendar.class) {
            @Mock
            public int get(int unit) {
                if (unit == Calendar.YEAR) {
                    return 2017;
                } else if (unit == Calendar.MONDAY) {
                    return 12;
                } else if (unit == Calendar.DAY_OF_MONTH) {
                    return 25;
                } else if (unit == Calendar.HOUR_OF_DAY) {
                    return 7;
                }
                return 0;
            }
        };

        // 从此Calendar的get方法，就沿用你定制过的逻辑，而不是它原先的逻辑。
        Calendar cal = Calendar.getInstance(Locale.FRANCE);
        Assert.assertEquals(2017, cal.get(Calendar.YEAR));
        Assert.assertEquals(12, cal.get(Calendar.MONDAY));
        Assert.assertEquals(25, cal.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(7, cal.get(Calendar.HOUR_OF_DAY));
        // Calendar的其它方法，不受影响
        Assert.assertTrue((cal.getFirstDayOfWeek() == Calendar.MONDAY));
    }
}

