package cn.xiaoyu.learning.unittest.testng.order;

/**
 * @author roin.zhang
 * @date 2020/4/24
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
