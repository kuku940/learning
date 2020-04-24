package cn.xiaoyu.learning.kafka.stream;

import cn.xiaoyu.learning.kafka.stream.domain.Purchase;
import cn.xiaoyu.learning.kafka.stream.domain.PurchasePattern;
import cn.xiaoyu.learning.kafka.stream.domain.RewardAccumulator;
import org.apache.kafka.common.serialization.Serde;

public class StreamSerdes {
    public static Serde<Purchase> PurchaseSerde() {
        return new PurchaseSerde();
    }

    public static Serde<PurchasePattern> PurchasePatternSerde() {
        return new PurchasePatternSerde();
    }

    public static Serde<RewardAccumulator> RewardAccumulatorSerde() {
        return new RewardAccumulatorSerde();
    }

    public static final class PurchaseSerde extends WrapperSerde<Purchase> {
        public PurchaseSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(Purchase.class));
        }
    }

    public static final class PurchasePatternSerde extends WrapperSerde<PurchasePattern> {
        public PurchasePatternSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(PurchasePattern.class));
        }
    }

    public static final class RewardAccumulatorSerde extends WrapperSerde<RewardAccumulator> {
        public RewardAccumulatorSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(RewardAccumulator.class));
        }
    }
}