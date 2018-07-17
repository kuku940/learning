package cn.xiaoyu.mock;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.easymock.EasyMock.anyInt;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({User.class})
public class TestMock {
    @Test
    public void test() throws Exception {
        /** 创建模拟成员 */
        Calculator calculator = EasyMock.mock(Calculator.class);
        EasyMock.expect(calculator.calculate(anyInt(), anyInt()))
                .andReturn(-1).once()
                .andThrow(new RuntimeException("error occur")).once()
                .andReturn(0).anyTimes();
        EasyMock.replay(calculator); // 重放Mock对象

        // Mockito是mock扩展，mockito并不支持静态的mock，所以引入PowerMockito实现对静态类的mock
        PowerMockito.mockStatic(User.class);
        PowerMockito.when(User.class, "sayHello", Mockito.anyString()).thenReturn("Hi Jack!");

        // Calculator方法测试
        assertEquals(-1, calculator.calculate(1, 1)); // 目标类调用目标方法
        try {
            calculator.calculate(1, 1);
            fail("error occur");
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
        // 目标类调用目标方法
        assertEquals(0, calculator.calculate(1, 1));
        // 验证Mock对象
        EasyMock.verify(calculator);

        // 验证静态方法是否正确
        assertEquals("Hi Jack!", User.sayHello("Marry"));
    }

}
