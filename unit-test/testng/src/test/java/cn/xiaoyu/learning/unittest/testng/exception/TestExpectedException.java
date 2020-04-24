package cn.xiaoyu.learning.unittest.testng.exception;

import org.testng.annotations.Test;

/**
 * @author roin.zhang
 * @date 2020/4/24
 */
public class TestExpectedException {
    @Test(expectedExceptions = ArithmeticException.class)
    public void divisionWithException() {
        int i = 1 / 0;
        System.out.println("After division the value of i is :" + i);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "NullPoint")
    public void testException() {
        throw new IllegalArgumentException("NullPoint");
    }
}
