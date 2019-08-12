package cn.xiaoyu.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SimpleConsumer {
    private static final String groupId = "test";
    private static final String[] subject = new String[]{"topic.out"};

    public static void main(String[] args) {
        // 推荐线程池方式启动
        new CommonConsumer(groupId, subject, new ConsumerListener() {
            @Override
            public void consumer(ConsumerRecord<String, String> record) {
                System.out.printf("offset = %d, key = %s, value = %s \n",
                        record.offset(), record.key(), record.value());
            }
        }).start();
    }
}
