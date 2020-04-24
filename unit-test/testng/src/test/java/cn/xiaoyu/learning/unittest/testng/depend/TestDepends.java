package cn.xiaoyu.learning.unittest.testng.depend;

import org.testng.annotations.Test;

/**
 * @author roin.zhang
 * @date 2020/4/24
 */
public class TestDepends {
    @Test(dependsOnMethods = {"setupEnv"})
    public void testMessage() {
        System.out.println("this is test message");
    }

    @Test
    public void setupEnv() {
        System.out.println("this is setup Env");
    }
}
