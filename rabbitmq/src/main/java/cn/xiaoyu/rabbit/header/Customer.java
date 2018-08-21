package cn.xiaoyu.rabbit.header;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author Roin zhang
 * @date 2018/8/21
 */

public class Customer {
    private final static String EXCHANGE_NAME = "header-exchange";
    private final static String QUEUE_NAME = "header-queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");

        factory.setPort(AMQP.PROTOCOL.PORT);

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        // 声明转发器和类型headers
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.HEADERS, false, true, null);
        channel.queueDeclare(QUEUE_NAME, false, false, true, null);

        Map<String, Object> headers = new Hashtable<String, Object>();
        // 匹配有两种方式all和any
        headers.put("x-match", "any");
        headers.put("username", "Tom");
        headers.put("age", 22);

        // 为转发器指定队列，设置binding 绑定header 键值对
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "", headers);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                channel.basicAck(envelope.getDeliveryTag(), false);

                String bodyStr = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + bodyStr + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
    }
}
