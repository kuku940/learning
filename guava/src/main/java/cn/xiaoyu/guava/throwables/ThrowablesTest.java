package cn.xiaoyu.guava.throwables;

import com.google.common.base.Throwables;

import java.io.IOException;

/**
 * 异常处理类
 */

public class ThrowablesTest {
    public static void main(String[] args) {
        ThrowablesTest throwablesTest = new ThrowablesTest();
        try {
            throwablesTest.showcaseThrowables();
        } catch (InvalidInputException e) {
            System.out.println(Throwables.getRootCause(e));
        } catch (Exception e) {
            Throwables.getStackTraceAsString(e);
        }

        try {
            throwablesTest.showcaseThrowables1();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }

    public void showcaseThrowables() throws InvalidInputException {
        try {
            sqrt(-3.0);
        } catch (Throwable e) {
            Throwables.throwIfInstanceOf(e, InvalidInputException.class);
//            Throwables.propagate(e);
        }
    }

    public void showcaseThrowables1() {
        try {
            int[] data = {1, 2, 3};
            getValue(data, 4);
        } catch (Throwable e) {
            Throwables.throwIfInstanceOf(e, IndexOutOfBoundsException.class);
//            Throwables.propagate(e);
        }
    }

    public double sqrt(double input) throws InvalidInputException {
        if (input < 0) {
            throw new InvalidInputException();
        }
        return Math.sqrt(input);
    }

    public double getValue(int[] list, int index) throws IndexOutOfBoundsException {
        return list[index];
    }

    public void dummyIO() throws IOException {
        throw new IOException();
    }

}

class InvalidInputException extends Exception {
}