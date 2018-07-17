package cn.xiaoyu.guava.precondition;

import com.google.common.base.Preconditions;

/**
 * Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数。
 */
public class PreconditionsTest {
    public static void main(String[] args) {
        PreconditionsTest preconditionsTest = new PreconditionsTest();
        try {
            System.out.println(preconditionsTest.sqrt(-3.0));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(preconditionsTest.sum(null, 3));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(preconditionsTest.getValue(6));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public double sqrt(double input) {
        Preconditions.checkArgument(input > 0.0,
                "Illegal Argument passed: Negative value %s.", input);
        return Math.sqrt(input);
    }

    public int sum(Integer a, Integer b) {
        a = Preconditions.checkNotNull(a, "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b, "Illegal Argument passed: Second parameter is Null.");
        return a + b;
    }

    public int getValue(int input) {
        int[] data = {1, 2, 3, 4, 5};
        Preconditions.checkElementIndex(input, data.length, "Illegal Argument passed: Invalid index.");
        return 0;
    }
}
