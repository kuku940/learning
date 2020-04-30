package cn.xiaoyu.rabbit.routing;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author Roin zhang
 * @date 2018/8/16
 */

public class ReceiveLogsDirect2 {
    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String[] routingKeys = {"error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 获取匿名队列名称
        String queueName = channel.queueDeclare().getQueue();

        // 根据路由关键字进行多重绑定
        for (String severity : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, severity);
            System.out.println("ReceiveLogsDirect2 exchange:" + EXCHANGE_NAME + ", queue:" + queueName + ", BindRoutingKey:" + severity);
        }
        System.out.println("ReceiveLogsDirect2 [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println(" [2] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
