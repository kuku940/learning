package cn.xiaoyu.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class CommonConsumer extends Thread {
    private String groupId;
    private String[] subject;
    private ConsumerListener listener;
    private KafkaConsumer<String, String> consumer;

    public CommonConsumer(String groupId, String[] subject, ConsumerListener listener) {
        this.groupId = groupId;
        this.subject = subject;
        this.listener = listener;
    }

    /**
     * 初始化kafka消费者
     */
    private void init() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9091,master:9092,master:9093");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(subject));
    }

    /**
     * 消费消息
     */
    private void consume() {
        System.out.println("Subscribed to topic " + subject);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, String> record : records) {
                listener.consumer(record);
            }
        }
    }

    @Override
    public void run() {
        init();
        consume();
    }
}
