package cn.xiaoyu.rabbit.topic;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 用模糊匹配的方式来订阅感兴趣的内容
 *
 * @author Roin zhang
 * @date 2018/8/16
 */

public class ReceiveLogsTopic2 {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明一个匹配模式的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        // 路由关键字 * - 表示一个单词，# - 表示一个或多个单词
        String[] routingKeys = {"*.*.rabbit", "lazy.#"};
        // 绑定路由关键字
        for (String bindingKey : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            System.out.println("ReceiveLogsTopic2 exchange:" + EXCHANGE_NAME + ", queue:" + queueName + ", BindRoutingKey:" + bindingKey);
        }
        System.out.println("ReceiveLogsTopic2 [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("ReceiveLogsTopic2 [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
