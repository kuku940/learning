package cn.xiaoyu.learning.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 简易生产者
 */
public class SimpleProducer {
    public static void main(String[] args) {
        String topicName = "topic.test";

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9091,master:9092,master:9093");
        // 判断请求是否成功发送
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 请求失败，生产者重试次数。启动重试，会有重复消息的可能性
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        // 生产者缓存分区未发送的消息
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 生产者可用的缓存总量
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            // 创建发送消息记录
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName,
                    Integer.toString(i), Integer.toString(i));
            // send是异步的，添加消息到缓冲区等待发送，并立即返回 Fire-and-forget
//            producer.send(record);
            // 阻塞调用 Synchronous send
//            RecordMetadata metadata = producer.send(record).get();
            // 无阻塞回调，利用回调参数来接受回调通知 Asynchronous send
            producer.send(record, (metadata, e) -> {
                // 一般会在生产者的IO线程中执行，对于有阻塞或者耗时的回调，可以交给线程池处理
                if (e != null) {
                    e.printStackTrace();
                }
                System.out.println("The offset of the record we just sent is: " + metadata.offset());
            });
        }

        System.out.println("Message sent successfully");
        producer.close();
    }
}
