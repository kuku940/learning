package cn.xiaoyu.jmockit.base;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpSession;

/**
 * 注释@Mocked使用 - 帮助生成实例
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class JMockit002MockedTest {
    /**
     * 当接口或者类加上@Mocked，JMockit会帮我们实例化对象，不会为null
     */
    @Mocked
    private HelloJMockit helloJMockit;

    @Test
    public void sayHiTest() {
        // 录制 - Record - 即先录制某类/对象的某个方法调用，在当输入什么时，返回什么
        new Expectations() {{
            // 期待helloJMockit.sayHello() 的返回结果是："Hello Jack"
            helloJMockit.sayHello();
            result = "Hello Jack!";
        }};

        // 重放 - Replay - 重放测试逻辑
        Assert.assertEquals("Hello Jack!", helloJMockit.sayHello());

        // 验证 - Verification - 重放后的验证。比如验证某个方法有没有被调用，调用多少次
        new Verifications() {{
            helloJMockit.sayHello();
            times = 1;
        }};
    }

    @Test
    public void sayHi2Test(@Mocked HttpSession httpSession) {
        // （返回类型为String）也不起作用了，返回了null
        Assert.assertNull(httpSession.getId());
        // （返回类型为原始类型）也不起作用了，返回了0
        Assert.assertEquals(0L, httpSession.getCreationTime());
        // (返回类型为原非始类型，非String，返回的对象不为空，这个对象也是JMockit帮你实例化的，同样这个实例化的对象也是一个Mocked对象)
        Assert.assertNotNull(httpSession.getServletContext());
        // Mocked对象返回的Mocked对象，（返回类型为String）的方法也不起作用了，返回了null
        Assert.assertNull(httpSession.getServletContext().getContextPath());
    }
}