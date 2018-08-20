package cn.xiaoyu.rabbit.hellowolrd;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 *
 * @author Roin zhang
 * @date 2018/8/16
 */

public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ地址
        factory.setHost("localhost");

        // 建立到代理服务器的连接
        Connection conn = factory.newConnection();
        // 获得信道
        Channel channel = conn.createChannel();

        // 声明队列，在RobbitMq中，队列声明是幂等性的
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发布消息到队列中
        String message = "hello world";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        // 关闭通道和连接
        channel.close();
        conn.close();
    }
}
