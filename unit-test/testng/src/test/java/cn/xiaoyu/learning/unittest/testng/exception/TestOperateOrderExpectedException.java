package cn.xiaoyu.learning.unittest.testng.exception;

import cn.xiaoyu.learning.unittest.testng.order.Order;
import cn.xiaoyu.learning.unittest.testng.order.OrderBo;
import cn.xiaoyu.learning.unittest.testng.order.OrderNotFoundException;
import cn.xiaoyu.learning.unittest.testng.order.OrderSaveException;
import cn.xiaoyu.learning.unittest.testng.order.OrderUpdateException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 如果ClassName类抛出了异常，测算测试通过，没有异常算测试不通过
 * expectedExceptions的值也可以是一个数组。
 *
 * @author roin.zhang
 * @date 2020/4/24
 */
public class TestOperateOrderExpectedException {
    OrderBo orderBo;
    Order data;

    @BeforeTest
    void setup() {
        orderBo = new OrderBo();
        data = new Order();
        data.setId(1000L);
    }

    @Test(expectedExceptions = OrderSaveException.class)
    public void throwIfOrderIsNull() throws OrderSaveException {
        orderBo.save(null);
    }

    @Test(expectedExceptions = {OrderUpdateException.class, OrderNotFoundException.class})
    public void throwIfOrderIsNotExists() throws OrderUpdateException, OrderNotFoundException {
        orderBo.update(data);
    }
}
