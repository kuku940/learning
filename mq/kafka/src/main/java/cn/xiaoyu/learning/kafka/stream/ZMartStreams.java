package cn.xiaoyu.learning.kafka.stream;

import cn.xiaoyu.learning.kafka.stream.domain.Purchase;
import cn.xiaoyu.learning.kafka.stream.domain.PurchasePattern;
import cn.xiaoyu.learning.kafka.stream.domain.RewardAccumulator;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

/**
 * Kafka Streams流处理步骤
 * 1. 配置Kafka Streams
 * 2. 创建Serde实例
 * 3. 创建处理的拓扑
 * 4. 创建和启动KStream
 *
 * @link https://blog.csdn.net/gangchengzhong/article/details/84023095
 */
public class ZMartStreams {
    public static void main(String[] args) {
        // 配置Kafka Stream流
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 创建Serde实例
        Serde<String> stringSerde = Serdes.String();
        Serde<Purchase> purchaseSerde = StreamSerdes.PurchaseSerde();
        Serde<PurchasePattern> purchasePatternSerde = StreamSerdes.PurchasePatternSerde();
        Serde<RewardAccumulator> rewardAccumulatorSerde = StreamSerdes.RewardAccumulatorSerde();

        // 创建处理的拓扑
        StreamsBuilder builder = new StreamsBuilder();
        // 处理器1：隐藏信用卡信息
        KStream<String, Purchase> purchaseKStream = builder
                .stream("transacations", Consumed.with(stringSerde, purchaseSerde))
                .mapValues(p -> Purchase.builder(p).maskCreditCard().build());

        // 处理器2：提取抽取消费地点的zip code
        KStream<String, PurchasePattern> patternKStream = purchaseKStream
                .mapValues(purchase -> PurchasePattern.builder(purchase).build());
        // 将结果发送给另外一个负责分析模式的topic
        patternKStream.to("patterns", Produced.with(stringSerde, purchasePatternSerde));

        // 处理器3：负责提取客户编号和消费金额，计算奖励积分
        KStream<String, RewardAccumulator> rewardKStream = purchaseKStream
                .mapValues(purchase -> RewardAccumulator.builder(purchase).build());
        rewardKStream.to("rewards", Produced.with(stringSerde, rewardAccumulatorSerde));

        // 处理器4：负责保存所有的消费信息，并过滤小额购买
        KStream<String, Purchase> filteredStream = purchaseKStream
                .filter((key, purchase) -> purchase.getPrice() > 5.00);
        filteredStream.to("purchases", Produced.with(stringSerde, purchaseSerde));

        // 处理器:将电子产品和咖啡的销售数据分离
        KStream<String, Purchase>[] kstreamByDept = purchaseKStream.branch(
                (key, purchase) -> purchase.getDepartment().equalsIgnoreCase("coffee"),
                (key, purchase) -> purchase.getDepartment().equalsIgnoreCase("electronics"));
        kstreamByDept[0].to("coffee", Produced.with(stringSerde, purchaseSerde));
        kstreamByDept[1].to("electronics", Produced.with(stringSerde, purchaseSerde));

        // 控制台打印消费者输出结果
        patternKStream.print(Printed.<String, PurchasePattern>toSysOut().withLabel("patterns"));
        rewardKStream.print(Printed.<String, RewardAccumulator>toSysOut().withLabel("rewards"));
        filteredStream.print(Printed.<String, Purchase>toSysOut().withLabel("purchases"));

        // 创建和启动KStream
        try (KafkaStreams streams = new KafkaStreams(builder.build(), props)) {
            streams.start();
        }
    }
}
