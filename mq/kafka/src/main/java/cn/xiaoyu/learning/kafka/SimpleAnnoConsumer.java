package cn.xiaoyu.learning.kafka;

import cn.xiaoyu.learning.kafka.component.Consume;
import cn.xiaoyu.learning.kafka.component.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@KafkaConsumer
public class SimpleAnnoConsumer implements ConsumerListener {
    @Override
    @Consume(subject = "topic.in", groupId = "anno")
    public void consumer(ConsumerRecord<String, String> record) {
        System.out.printf("offset = %d, key = %s, value = %s \n",
                record.offset(), record.key(), record.value());
    }
}
