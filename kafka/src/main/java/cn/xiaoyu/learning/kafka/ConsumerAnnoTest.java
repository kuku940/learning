package cn.xiaoyu.learning.kafka;

import cn.xiaoyu.learning.kafka.component.Consume;
import cn.xiaoyu.learning.kafka.component.KafkaConsumer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ConsumerAnnoTest {
    public static void main(String[] args) throws Exception {
        // 通过SPI的方式来获取ConsumerListener的所有子类实现
        ServiceLoader<ConsumerListener> loader = ServiceLoader.load(ConsumerListener.class);
        Iterator<ConsumerListener> iterator = loader.iterator();
        while (iterator.hasNext()) {
            ConsumerListener listener = iterator.next();
            System.out.println(listener.getClass().getName());
            execute(listener.getClass());
        }
    }

    /**
     * 获取注释修饰方法，解析groupId和subject并启动线程池
     *
     * @param clazz
     * @throws Exception
     */
    private static void execute(Class clazz) throws Exception {
        // 如果类不包含@Table直接结束
        if (!clazz.isAnnotationPresent(KafkaConsumer.class)) {
            return;
        }

        Constructor constructor = clazz.getConstructor();
        ConsumerListener consumer = (ConsumerListener) constructor.newInstance();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 如果该字段不包含@Column注解，跳过
            if (!method.isAnnotationPresent(Consume.class)) {
                continue;
            }

            Consume km = method.getAnnotation(Consume.class);
            String groupId = km.groupId();
            String subject = km.subject();
            System.out.println(groupId + "-" + subject);

            // 推荐线程池方式启动
            new CommonConsumer(groupId, new String[]{subject}, consumer).start();
        }
    }
}
