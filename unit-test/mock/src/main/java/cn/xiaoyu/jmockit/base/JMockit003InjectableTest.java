package cn.xiaoyu.jmockit.base;

import mockit.Injectable;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * 注释@Injectable的使用
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class JMockit003InjectableTest {
    /**
     * 注释@Mocked和@Injectable都是生成Mocked对象
     * 注释@Injectable只是针对其修饰的实例，而@Mocked是针对其修饰类的所有实例
     */
    @Test
    public void mockedTest(@Mocked Locale locale) {
        // 静态方法不起作用了,返回了null
        Assert.assertNull(Locale.getDefault());
        // 非静态方法（返回类型为String）也不起作用了，返回了null
        Assert.assertNull(locale.getCountry());
        // 自已new一个，也同样如此，方法都被mock了
        Locale chinaLocale = new Locale("zh", "CN");
        Assert.assertNull(chinaLocale.getCountry());
    }


    @Test
    public void injectableTest(@Injectable Locale locale) {
        // 静态方法不mock
        Assert.assertNotNull(Locale.getDefault());
        // 非静态方法（返回类型为String）也不起作用了，返回了null,但仅仅限于locale这个对象
        Assert.assertNull(locale.getCountry());

        // 自已new一个，并不受影响
        Locale chinaLocale = new Locale("zh", "CN");
        Assert.assertEquals("CN", chinaLocale.getCountry());
    }
}
