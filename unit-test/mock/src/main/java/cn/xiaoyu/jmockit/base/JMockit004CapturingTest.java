package cn.xiaoyu.jmockit.base;

import mockit.Capturing;
import mockit.Expectations;
import mockit.Verifications;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.proxy.Proxy;

/**
 * 注释@Capturing
 * 只知道父类或接口时，但需要控制它所有子类的行为时，子类可能有多个实现（可能有人工写的，也可能是AOP代理自动生成时），就用@Capturing。
 * <p>
 * 测试时会出现java.lang.VerifyError，使用jdk1.6或者以下方式：
 * 如果是jdk7：请填写：-XX:-UseSplitVerifier
 * 如果是jdk8：请填写：-noverify
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class JMockit004CapturingTest {
    private Long testUserId = 123456L;
    private IPrivilege privilegeManager1 = userId -> testUserId != userId;

    /**
     * 权限检验类，可能是JDK动态代理生成。我们通常AOP来做权限校验。
     */
    private IPrivilege privilegeManager2 = (IPrivilege) Proxy.newProxyInstance(IPrivilege.class.getClassLoader(),
            new Class[]{IPrivilege.class}, (proxy, method, args) -> (long) args[0] != testUserId);

    @Test
    public void caputringTest(@Capturing IPrivilege privilegeManager) {
        new Expectations() {{
            privilegeManager.isAllow(testUserId);
            result = true;
        }};

        // 不管权限验证的实现类是哪个，这个测试用户都有权限
        Assert.assertTrue(privilegeManager1.isAllow(testUserId));
        Assert.assertTrue(privilegeManager2.isAllow(testUserId));

        // 验证阶段
        new Verifications() {{
            privilegeManager.isAllow(testUserId);
            times = 2;
        }};
    }

    @Test
    public void withoutCaputringTest() {
        // 不管权限校验的实现类是哪个，这个测试用户没有权限
        Assert.assertFalse(privilegeManager1.isAllow(testUserId));
        Assert.assertFalse(privilegeManager2.isAllow(testUserId));
    }
}

