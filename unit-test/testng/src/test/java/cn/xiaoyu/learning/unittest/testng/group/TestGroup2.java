package cn.xiaoyu.learning.unittest.testng.group;

import org.testng.annotations.Test;

/**
 * @author roin.zhang
 * @date 2020/4/24
 */
public class TestGroup2 {
    @Test(groups = {"oracle", "mysql"})
    public void close() {
        System.out.println("close Connection - 1");
    }

    @Test(groups = "oracle")
    public void testConnectOracle() {
        System.out.println("testConnectOracle - 1");
    }

    @Test(groups = "mysql")
    public void testConnectMsSQL() {
        System.out.println("testConnectMsSQL - 1");
    }
}
