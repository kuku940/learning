package cn.xiaoyu.learning.unittest.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author roin.zhang
 * @date 2020/4/24
 */
public class TestHelloWorld {
    @Test
    public void tester1() {
        HelloWorld hello = new HelloWorld();
        String helloworld = hello.hello();

        //测试返回结果不为空
        Assert.assertNotNull(helloworld);
        //测试返回结果为”hello world !“字符串
        Assert.assertEquals(helloworld, "hello world!");
    }

    /**
     * 表示测试方法是否执行
     */
    @Test(enabled = false)
    public void tester2() {
        HelloWorld hello = new HelloWorld();
        String helloworld = hello.hello();

        Assert.assertEquals(helloworld, "");
    }

    /**
     * 超时测试
     */
    @Test(timeOut = 100)
    public void testThisShouldPass() throws InterruptedException {
        Thread.sleep(50);
    }

    @Test(timeOut = 100, enabled = false)
    public void testThisShouldFail() {
        while (true) {
            // do nothing
        }
    }
}
