package cn.xiaoyu.learning.java8.defaultfunction;

interface Vehicle {
    /**
     * 静态默认方法
     */
    static void blowHorn() {
        System.out.println("按喇叭！");
    }

    /**
     * 默认方法
     */
    default void print() {
        System.out.println("我是一辆汽车！");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车！");
    }
}

/**
 * 接口的默认方法和静态方法
 * 指接口可以实现默认方法，而不需要实现类去实现
 */
public class DefaultFunction {
    public static void main(String[] args) {
        Car car = new Car();
        car.print();
    }
}

class BWM implements Vehicle, FourWheeler {
    @Override
    public void print() {
        System.out.println("我是宝马！");
    }
}

class Car implements Vehicle, FourWheeler {
    @Override
    public void print() {
        FourWheeler.super.print();
        Vehicle.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车！");
    }
}
