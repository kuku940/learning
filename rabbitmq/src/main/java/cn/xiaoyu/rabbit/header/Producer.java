package cn.xiaoyu.rabbit.header;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Header 交换器类型
 *
 * @author Roin zhang
 * @date 2018/8/21
 */

public class Producer {
    private final static String EXCHANGE_NAME = "header-exchange";

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
        String message = new Date().toLocaleString() + ": log something";

        Map<String, Object> headers = new Hashtable<String, Object>();
        headers.put("username", "Tom");
        headers.put("age", 20);
        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.headers(headers);

        // 指定消息发送的转发器，绑定键值对heanders键值对
        channel.basicPublish(EXCHANGE_NAME, "", properties.build(), message.getBytes());

        System.out.println("Send message:" + message);
        channel.close();
        conn.close();
    }
}
