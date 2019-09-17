package cn.xiaoyu.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Roin zhang
 * @date 2018/7/25
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Calculator.class})
@PowerMockIgnore({"javax.management.*", "javax.security.*"})
public class TestPowerMock {
    /**
     * 模拟静态方法
     */
    @Test
    public void testStaticMethod() {
        PowerMockito.mockStatic(Calculator.class);
        PowerMockito.when(Calculator.add(1, 2)).thenReturn(4);

        Assert.assertEquals(4, Calculator.add(1, 2));
        PowerMockito.verifyStatic(Calculator.class);
        System.out.println(Calculator.add(1, 2));
    }
}

