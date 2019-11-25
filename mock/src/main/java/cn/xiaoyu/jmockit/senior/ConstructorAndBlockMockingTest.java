package cn.xiaoyu.jmockit.senior;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

/**
 * Mock构造函数和初始代码块
 *
 * @author Roin zhang
 * @date 2018/7/25
 */

public class ConstructorAndBlockMockingTest {
    @Test
    public void testClassMockingByMockUp() {
        new AnOrdinaryWithBlockMockUp();
        AnOrdinaryClassWithBlock instance = new AnOrdinaryClassWithBlock(10);

        // 静态初始代码块mock
        Assert.assertEquals(0, AnOrdinaryClassWithBlock.j);
        // 构造函数和初始代码块被mock
        Assert.assertEquals(0, instance.getI());
    }

    public static class AnOrdinaryWithBlockMockUp extends MockUp<AnOrdinaryClassWithBlock> {
        @Mock
        public void $init(int i) { // Mock构造函数和初始代码块，函数名$init就代表类的构造函数

        }

        @Mock
        public void $clinit() { // Mock静态初始代码块，函数名就代表类的静态代码快

        }
    }

}

class AnOrdinaryClassWithBlock {
    public static int j;

    // 静态初始代码块
    static {
        j = 2;
    }

    private int i;

    // 初始代码块
    {
        i = 1;
    }

    // 构造函数
    public AnOrdinaryClassWithBlock(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

}