package cn.xiaoyu.rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 简单消息示例 - 生产者
 */
public class SimpleProducer {
  public static void main(String[] args) throws Exception {
    startAsync();
  }

  /**
   * 同步传输 - 用于重要通知消息，短信通知，短信营销系统等
   *
   * @throws Exception
   */
  public static void startSync() throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("mq-group");
    producer.setNamesrvAddr("127.0.0.1:9876");
    producer.setInstanceName("producer");
    producer.start();

    for (int i = 0; i < 10; i++) {
      Thread.sleep(1000);
      Message msg =
          new Message(
              "TopicTest",
              "TagA",
              ("RocketMQ message " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
      // 发送消息
      SendResult result = producer.send(msg);
      System.out.printf("%s%n", result);
    }

    producer.shutdown();
  }

  /**
   * 异步传输 - 用于响应时间敏感的业务场景
   *
   * @throws Exception
   */
  private static void startAsync() throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("mq-group");
    producer.setNamesrvAddr("127.0.0.1:9876");
    producer.setInstanceName("producer");
    producer.start();
    producer.setRetryTimesWhenSendAsyncFailed(0);
    for (int i = 0; i < 10; i++) {
      final int index = 1;
      Message msg =
          new Message(
              "TopicTest",
              "TagA",
              "OrderId188",
              ("Hello World").getBytes(RemotingHelper.DEFAULT_CHARSET));

      producer.send(
          msg,
          new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
              System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable e) {
              System.out.printf("%-10d Exception %s %n", index, e);
              e.printStackTrace();
            }
          });

      producer.shutdown();
    }
  }

  /**
   * 单向传输 - 日志收集
   *
   * @throws Exception
   */
  public static void startOneWay() throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("mq-group");
    producer.setNamesrvAddr("127.0.0.1:9876");
    producer.setInstanceName("producer");
    producer.start();

    for (int i = 0; i < 10; i++) {
      Thread.sleep(1000);
      Message msg =
          new Message(
              "TopicTest",
              "TagA",
              ("RocketMQ message " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
      // 发送消息
      producer.sendOneway(msg);
    }

    producer.shutdown();
  }
}
