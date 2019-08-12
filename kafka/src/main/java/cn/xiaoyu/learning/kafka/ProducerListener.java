package cn.xiaoyu.learning.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * 生产者监听器
 */
public interface ProducerListener {
    /**
     * 回调函数
     *
     * @param metadata
     */
    void callback(RecordMetadata metadata);

    /**
     * 消息失败处理逻辑
     *
     * @param e
     */
    void onFail(Exception e);
}
