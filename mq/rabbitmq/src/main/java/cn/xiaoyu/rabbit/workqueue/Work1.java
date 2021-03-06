package cn.xiaoyu.rabbit.workqueue;

import cn.xiaoyu.rabbit.common.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author Roin zhang
 * @date 2018/8/16
 */

public class Work1 {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        final Connection connection = ConnectionUtils.getConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("Worker1 [*] Waiting for messages. To exit press CTRL+C");

        // 每次从队列中获取数量
        channel.basicQos(1);
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);

                System.out.println("Worker1 [x] Received '" + message + "'");
                try {
                    doWork(message);
                } finally {
                    // 消息处理完成确认
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    System.out.println("Worker1 [x] Done");
                }
            }
        };
        // 消息消费完成确认
        channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
    }

    private static void doWork(String task) {
        try {
            // 暂停一秒
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
