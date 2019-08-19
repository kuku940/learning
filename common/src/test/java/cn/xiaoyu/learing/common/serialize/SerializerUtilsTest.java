package cn.xiaoyu.learing.common.serialize;

import cn.xiaoyu.learing.common.domain.Person;
import cn.xiaoyu.learning.common.serialize.SerializerUtils;
import org.junit.Test;

/**
 * @author roin.zhang
 * @date 2019-08-19
 */
public class SerializerUtilsTest {
    @Test
    public void testFastjson(){
        Person person = new Person("Jack", 18);
        String json = SerializerUtils.FASTJSON.serialize(person, "UTF-8");
        System.out.println(json);

        Person p1 = SerializerUtils.FASTJSON.deserialize(json, Person.class);
        System.out.println(p1);
    }

    @Test
    public void testJackson(){
        Person person = new Person("Pony", 20);
        String json = SerializerUtils.JACKSON.serialize(person, "UTF-8");
        System.out.println(json);

        Person p1 = SerializerUtils.JACKSON.deserialize(json, Person.class);
        System.out.println(p1);
    }
}
