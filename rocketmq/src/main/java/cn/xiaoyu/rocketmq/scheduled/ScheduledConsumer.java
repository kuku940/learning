package cn.xiaoyu.rocketmq.scheduled;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * 定时消息 - 消费者
 * 指定的时间执行
 */
public class ScheduledConsumer {

  public static void main(String[] args) throws Exception {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
    consumer.subscribe("TestTopic", "*");

    consumer.registerMessageListener((MessageListenerConcurrently) (msgs, ctx) -> {
      msgs.forEach(msg -> System.out.println("Receive message[msgId=" + msg.getMsgId() + "]"
          + (System.currentTimeMillis() - msg.getStoreTimestamp()) + "ms later"));
      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    });
  }
}
