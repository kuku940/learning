package cn.xiaoyu.powermock;

/**
 * @author roin.zhang
 * @date 2019-09-17
 */
public class Calculator {
    public static int add(int i, int j) {
        System.out.println(i + " + " + j + "=" + (i + j));
        return i + j;
    }

    public int sub(int i, int j) {
        System.out.println(i + " - " + j + "=" + (i - j));
        return i - j;
    }

    private int mul(int i, int j) {
        System.out.println(i + " * " + j + "=" + (i * j));
        return i * j;
    }
}