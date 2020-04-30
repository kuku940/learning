package cn.xiaoyu.rabbit.topic;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author Roin zhang
 * @date 2018/8/16
 */

public class TopicSend {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) {
        Connection conn = null;
        Channel channel;
        try {
            conn = ConnectionUtils.getConnection();
            channel = conn.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String[] routingKeys = {"quick.orange.rabbit",
                    "lazy.orange.elephant",
                    "lazy.orange.fox",
                    "quick.orange.fox",
                    "lazy.brown.fox",
                    "quick.brown.fox",
                    "quick.orange.male.rabbit",
                    "lazy.orange.male.rabbit"};

            for (String severity : routingKeys) {
                String message = "From " + severity + " routingKey's message!";
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
                System.out.println("TopicSend [x] Sent '" + severity + "':'" + message + "'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
