package cn.xiaoyu.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Roin zhang
 * @date 2018/7/25
 */
@RunWith(PowerMockRunner.class)
public class TestPowerMock {
    /**
     * 模拟静态方法
     */
    @Test
    @PrepareForTest({Calculator.class})
    public void testStaticMethod() {
        PowerMockito.mockStatic(Calculator.class);
        PowerMockito.when(Calculator.add(1, 2)).thenReturn(4);

        Assert.assertEquals(4, Calculator.add(1, 2));
        PowerMockito.verifyStatic(Calculator.class);
        System.out.println(Calculator.add(1, 2));
    }
}

class Calculator {
    public static int add(int i, int j) {
        System.out.println(i + " + " + j + "=" + (i + j));
        return i + j;
    }

    public int sub(int i, int j) {
        System.out.println(i + " - " + j + "=" + (i - j));
        return i - j;
    }

    private int mul(int i, int j) {
        System.out.println(i + " * " + j + "=" + (i * j));
        return i * j;
    }
}

