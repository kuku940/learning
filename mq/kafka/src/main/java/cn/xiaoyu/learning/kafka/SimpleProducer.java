package cn.xiaoyu.learning.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleProducer {
    public static void main(String[] args) {
        CommonProducer producer = new CommonProducer("topic.in");
        producer.produce("key", "value", new ProducerListener() {
            @Override
            public void callback(RecordMetadata metadata) {
                System.out.println(metadata.offset());
            }

            @Override
            public void onFail(Exception e) {
                System.out.println("消费异常！");
            }
        });
        producer.close();
    }
}
