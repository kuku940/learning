package cn.xiaoyu.rabbit.common;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author roin.zhang
 * @date 2020/4/30
 */
public class ConnectionUtils {
    /**
     * 获取MQ的连接
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(AMQP.PROTOCOL.PORT);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("testhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 获取RabbitMQ连接
        return factory.newConnection();
    }
}
