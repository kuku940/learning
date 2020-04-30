package cn.xiaoyu.rabbit.routing;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 只订阅自己感兴趣的内容
 *
 * @author Roin zhang
 * @date 2018/8/16
 */

public class RoutingSendDirect {
    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String[] routingKeys = {"info", "warning", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        for (String severity : routingKeys) {
            String message = "Send the message level:" + severity;
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
