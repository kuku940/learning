package cn.xiaoyu.learning.kafka.simple;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.util.Properties;

/**
 * Kafka Streams流处理步骤
 * 1. 配置Kafka Streams
 * 2. 创建Serde实例
 * 3. 创建处理的拓扑
 * 4. 创建和启动KStream
 */
public class SimpleStreams {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 创建处理的拓扑
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream("topic.in",
                Consumed.with(Serdes.String(), Serdes.String()));
        KStream<String, String> wordCounts = textLines
                .mapValues(line -> line.toUpperCase());
        wordCounts.to("topic.out", Produced.with(Serdes.String(), Serdes.String()));

        // 创建和启动KStream
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}
