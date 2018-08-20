package cn.xiaoyu.jmockit.common;

/**
 * @author Roin zhang
 * @date 2018/7/25
 */

public class AnOrdinaryClass {
    /**
     * 静态方法
     *
     * @return
     */
    public static int staticMethod() {
        return 1;
    }

    /**
     * 普通方法
     *
     * @return
     */
    public int ordinaryMethod() {
        return 2;
    }

    /**
     * final方法
     *
     * @return
     */
    public final int finalMethod() {
        return 3;
    }

    /**
     * native方法,返回4
     *
     * @return
     */
    public native int navtiveMethod();

    /**
     * private方法
     *
     * @return
     */
    private int privateMethod() {
        return 5;
    }

    /**
     * 调用private方法
     *
     * @return
     */
    public int callPrivateMethod() {
        return privateMethod();
    }
}