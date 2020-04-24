package cn.xiaoyu.jmockit.common;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock接口
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class InterfaceMockingTest {
    @Injectable
    private AnOrdinaryInterface anOrdinaryInterface;

    @Test
    public void testInterfaceMockingByExpectation() {
        // 录制
        new Expectations() {{
            anOrdinaryInterface.method1();
            result = 10;
            anOrdinaryInterface.method2(withInstanceOf(List.class));
            result = 20;
        }};

        Assert.assertEquals(10, anOrdinaryInterface.method1());
        Assert.assertEquals(20, anOrdinaryInterface.method2(new ArrayList<>()));
    }

    @Test
    public void testInterfaceMockingByMockUp() {
        // 手工通过MockUp创建这个接口的实例
        MockUp<AnOrdinaryInterface> mockUp = new MockUp<AnOrdinaryInterface>(AnOrdinaryInterface.class) {
            @Mock
            public int method1() {
                return 10;
            }

            @Mock
            public int method2() {
                return 20;
            }
        };
//        AnOrdinaryInterface anOrdinaryInterface = mockUp.getMockInstance();
//
//        Assert.assertEquals(10, anOrdinaryInterface.method1());
//        Assert.assertEquals(20, anOrdinaryInterface.method2());
    }
}
