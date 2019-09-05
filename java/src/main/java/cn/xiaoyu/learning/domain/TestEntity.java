package cn.xiaoyu.learning.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author roin.zhang
 * @date 2019-08-27
 */
public class TestEntity<T extends BaseEntity> {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1));
        list.add(new Person(2));
        list.add(new Person(3));
        list.add(new Person());

        System.out.println(list.stream().map(Person::getAmount).reduce(BigDecimal::add).get());
    }

    public T getData(T t) {
        t.getPk();
        return t;
    }
}


class Person {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Person(int val) {
        this.amount = new BigDecimal(val);
    }
    public Person(){}
}