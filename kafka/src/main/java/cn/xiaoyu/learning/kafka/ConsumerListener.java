package cn.xiaoyu.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 消费者监听器
 */
public interface ConsumerListener {
    /**
     * 消费逻辑
     *
     * @param record
     */
    void consumer(ConsumerRecord<String, String> record);
}
