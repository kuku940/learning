package cn.xiaoyu.learning.java8.function.reference;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Supplier<T> {
    T get();
}

/**
 * 方法引用
 * <p>
 * 构造器引用                 Class::new
 * 静态方法引用               Class::static_method
 * 特定类的任意方法引用        Class::method
 * 特定对象的方法引用          instance::method
 */
public class FunctionReference {
    public static void main(String[] args) {
        // 够在方法的引用
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        // 静态方法的引用
        cars.forEach(Car::collide);
        // 特定类的任意方法引用
        cars.forEach(Car::repair);
        // 特定对象的方法引用
        final Car bwm = Car.create(Car::new);
        cars.forEach(bwm::follow);

        List<String> names = Arrays.asList("google", "facebook", "microsoft", "amazon");
        names.forEach(System.out::println);
    }
}

class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collide " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}
