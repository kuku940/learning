package cn.xiaoyu.learning.kafka.simple;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 简易的消费者
 */
public class SimpleConsumer {
    public static void main(String[] args) {
        String topicName = "topic.in";
//        String topicName = "topic.out";

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9091,master:9092,master:9093");
        // 消费者组 同名的消费者组采用队列模型，不同名的消费者组采用发布订阅模型
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // 最大心跳停止时间，超过则认为机器故障
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        // 自动提交偏移量 设置enable.auto.commit，偏移量有auto.commit.interval.ms控制自动提交的频率
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));
        // 正则表达式匹配多个topic
//        consumer.subscribe(Pattern.compile("topic.*"));

        System.out.println("Subscribed to topic " + topicName);

        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s \n",
                        record.offset(), record.key(), record.value());

                // 提交指定offset
                currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                if (record.offset() % 8 == 0) {
                    consumer.commitSync(currentOffsets, Duration.ofSeconds(1));
                }
            }

//            try {
//                // 手动异步提交偏移量 提交此次poll返回的最大offset
//                consumer.commitAsync((offsets, ex) -> {
//                    if (ex != null) {
//                        System.out.printf("Commit failed for offsets %s", offsets);
//                    }
//                });
//            } catch (CommitFailedException ex) {
//                ex.printStackTrace();
//            } finally {
//                // 同步提交
//                consumer.commitSync();
//            }
        }
    }
}
