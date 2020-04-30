package cn.xiaoyu.rabbit.publish;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布与订阅模式
 *
 * @author Roin zhang
 * @date 2018/8/16
 */

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection conn = ConnectionUtils.getConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 分发消息
        String[] names = {"Jack", "Pony", "Robin", "Robot", "Jame"};
        for (String name : names) {
            String message = "hello " + name;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("[x] Send '" + message + "'");
        }

        channel.close();
        conn.close();
    }
}
