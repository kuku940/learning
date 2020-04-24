package cn.xiaoyu.rocketmq.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * 简单消息示例 - 消费者
 */
public class SimpleConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mq-group");

        consumer.setNamesrvAddr("127.0.0.1：9876");
        consumer.setInstanceName("consumer");
        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(
                (MessageListenerConcurrently) (msgs, context) -> {
                    msgs.forEach(msg -> System.out.println(new String(msg.getBody())));

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                });

        consumer.start();
        System.out.println("Consumer Started...");
    }
}
