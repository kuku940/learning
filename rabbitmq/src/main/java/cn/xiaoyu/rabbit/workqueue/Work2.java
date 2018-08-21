package cn.xiaoyu.rabbit.workqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Roin zhang
 * @date 2018/8/16
 */

public class Work2 {
    private static AtomicInteger flag = new AtomicInteger(0);
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        // durable - 持久化
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("Worker2 [*] Waiting for messages. To exit press CTRL+C");

        // 每次从队列中获取数量
        channel.basicQos(1);
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println("Worker2 [x] Received '" + message + "'");
                try {
                    doWork(message);

                    // 消息处理完成确认
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    System.out.println("Worker2 [x] Done");
                } catch (Exception e) {
                    // requeue设置为true，这条消息会被重新存入队列，false则会把消息从队列中移除
                    channel.basicReject(envelope.getDeliveryTag(), true);
                    System.out.println("Worker2 [x] Fail");
                }
            }
        };
        // 消息消费完成确认
        channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
    }

    private static void doWork(String task) {
        try {
            int i = flag.incrementAndGet();
            if (i % 2 == 1) {
                // 模拟异常情况
                throw new RuntimeException("消费端处理异常");
            }
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
