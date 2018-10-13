package cn.xiaoyu.jmockit.base;

import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * 第一个简单的测试Demo
 *
 * @author Roin zhang
 * @date 2018/7/25
 */
public class JMockit001HelloTest {
    @Test
    public void sayHelloAtChinaTest() {
        new Expectations(Locale.class) {{
            Locale.getDefault();
            result = Locale.CHINA;
        }};

        Assert.assertEquals("你好，JMockit!", new HelloJMockit().sayHello());
    }

    @Test
    public void sayHelloAtUSATest() {
        new Expectations(Locale.class) {{
            Locale.getDefault();
            result = Locale.US;
        }};
        Assert.assertEquals("Hello，JMockit!", new HelloJMockit().sayHello());
    }
}

class HelloJMockit {
    public String sayHello() {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            // 在中国，就说中文
            return "你好，JMockit!";
        } else {
            // 在其它国家，就说英文
            return "Hello，JMockit!";
        }
    }
}