package cn.xiaoyu.learning.spring;

import org.springframework.cglib.beans.BeanCopier;

import java.text.NumberFormat;

/**
 * @author 01399268
 * @date 2020/9/27
 */
public class BeanCopierTest {
    public static void main(String[] args) {
        // Double大于10位时转换为科学计数法
        Person person = new Person("1", 1381234567890D);
        PersonStr personStr = new PersonStr();

        // 复制属性并添加转换器
        BeanCopier copier = BeanCopier.create(Person.class, PersonStr.class, true);
        NumberFormat nf = NumberFormat.getInstance();
        // 设置最大的保留小位数
        nf.setMaximumFractionDigits(6);
        // 整数部分不以,分割，默认为true
        nf.setGroupingUsed(false);

        copier.copy(person, personStr, (value, target, context) -> {
            if (value instanceof Double) {
                System.out.println("将" + context + "由Double类型转化成String类型");
                return nf.format(value);
            }
            return value;
        });

        System.out.println(person);
        System.out.println(personStr);
    }
}

class Person {
    private String name;
    private Double amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Person(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}

class PersonStr {
    private String name;
    private String amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PersonStr{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}