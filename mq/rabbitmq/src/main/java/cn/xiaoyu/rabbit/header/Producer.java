package cn.xiaoyu.rabbit.header;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
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
        Connection conn = ConnectionUtils.getConnection();
        Channel channel = conn.createChannel();

        // 声明转发器和类型headers
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.HEADERS, false, true, null);
        String message = new Date().toLocaleString() + ": log something";

        Map<String, Object> headers = new Hashtable<>();
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
