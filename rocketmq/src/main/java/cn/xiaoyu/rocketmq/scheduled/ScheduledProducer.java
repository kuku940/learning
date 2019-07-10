package cn.xiaoyu.rocketmq.scheduled;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 定时消息 - 生产者
 * 指定的时间后执行
 */
public class ScheduledProducer {
  public static void main(String[] args) throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer();
    producer.start();

    int totalMsg2Send = 10;
    for (int i = 0; i < totalMsg2Send; i++) {
      Message msg = new Message("TestTopic", ("Hello Scheduled message " + i).getBytes());
      msg.setDelayTimeLevel(3);
      producer.send(msg);
    }

    producer.shutdown();
  }
}
