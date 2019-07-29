package cn.xiaoyu.learning.kafka.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 简易的消费者
 */
public class SimpleConsumer {
    public static void main(String[] args) {
        String topicName = "topic.test";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        // 消费者组 同名的消费者组采用队列模型，不同名的消费者组采用发布订阅模型
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));

        System.out.println("Subscribed to topic " + topicName);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s \n",
                        record.offset(), record.key(), record.value());
            }
        }
    }
}
