package cn.xiaoyu.rocketmq.ordered;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 有序消息 - 生产者
 */
public class OrderedProducer {

    public static void main(String[] args) throws Exception {
        MQProducer producer = new DefaultMQProducer("OrderedGroup");
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

        for (int i = 0; i < 10; i++) {
            int orderId = i % 10;
            Message msg = new Message("TopicTestOrdered", tags[i % tags.length], "Key" + i,
                    ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(msg, (mqs, msg1, obj) -> {
                Integer id = (Integer) obj;
                int index = id % mqs.size();
                return mqs.get(index);
            }, orderId);

            System.out.printf("%s%n", result);

            producer.shutdown();
        }
    }
}
