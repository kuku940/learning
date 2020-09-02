package cn.xiaoyu.learning.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<Person> people = new ArrayList() {{
            add(new Person("A", 5));
            add(new Person("B", 5));
            add(new Person("B", 6));
        }};

        List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
        list.forEach(System.out::println);

        Map<String, Person> map = people.stream()
                .collect(Collectors.toMap(Person::getName, person -> person, (p1, p2) -> p2));
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}