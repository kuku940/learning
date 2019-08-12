package cn.xiaoyu.learning.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class CommonProducer {
    private String topicName;
    private Producer<String, String> producer;

    private void init() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9091,master:9092,master:9093");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    /**
     * 生产者关闭接口
     */
    public void close() {
        if (this.producer != null) {
            this.producer.close();
        }
    }

    /**
     * 生产消息
     *
     * @param key
     * @param value
     * @param listener
     */
    public void produce(String key, String value, ProducerListener listener) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
        if (listener != null) {
            producer.send(record, (metadata, e) -> {
                if (e != null) {
                    listener.onFail(e);
                }
                listener.callback(metadata);
            });
        }
    }

    public CommonProducer(String topicName) {
        this.topicName = topicName;
        init();
    }
}
