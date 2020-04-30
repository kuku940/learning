package cn.xiaoyu.rabbit.hellowolrd;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author Roin zhang
 * @date 2018/8/16
 */

public class Customer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立到代理服务器的链接
        Connection conn = ConnectionUtils.getConnection();
        // 获取信道
        final Channel channel = conn.createChannel();

        // 声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                System.out.println("消费的路由键：" + routingKey);
                System.out.println("消费的内容类型：" + contentType);
                long deliveryTag = envelope.getDeliveryTag();
                //确认消息
                channel.basicAck(deliveryTag, false);

                String bodyStr = new String(body, StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + bodyStr + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
    }
}
