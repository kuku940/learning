package cn.xiaoyu.learning.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import scala.collection.JavaConversions;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Java管理kafka的topic
 *
 * @author 01399268
 * @date 2020/9/10
 */
public class KafkaTopic {
    public static void main(String[] args) {

    }

    /**
     * 创建主题
     * kafka-topics.sh --zookeeper locahost:2181 --create --topic kafka-action --replication-factor 2 --partitions 3
     */
    public void createTopic() {
        ZkUtils zkUtils = ZkUtils.apply("localhost:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        AdminUtils.createTopic(zkUtils, "topic-20", 3, 1, new Properties(), RackAwareMode.Enforced$.MODULE$);
        zkUtils.close();
    }

    /**
     * 删除主题
     * kafka-topics.sh --zookeeper localhost:2181 --topic kafka-action --delete
     */
    public void deleteTopic() {
        ZkUtils zkUtils = ZkUtils.apply("localhost:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        // 删除topic 'topic-20'
        AdminUtils.deleteTopic(zkUtils, "topic-20");
        zkUtils.close();
    }

    /**
     * 修改主题配置
     * kafka-config --zookeeper localhost:2181 --entity-type topics --entity-name kafka-action
     * --alter --add-config max.message.bytes=202480 --alter --delete-config flush.messages
     */
    public void updateTopic() {
        ZkUtils zkUtils = ZkUtils.apply("localhost:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), "topic-19");
        // 增加topic级别属性
        props.put("min.cleanable.dirty.ratio", "0.3");
        // 删除topic级别属性
        props.remove("max.message.bytes");
        // 修改topic 'test'的属性
        AdminUtils.changeTopicConfig(zkUtils, "test", props);
        zkUtils.close();
    }

    /**
     * 查看所有主题
     * kafka-topics.sh --zookeeper localhost:2181 --list
     */
    public static void listAllTopic() {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply("localhost:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
            List<String> topics = JavaConversions.seqAsJavaList(zkUtils.getAllTopics());
            topics.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zkUtils != null) {
                zkUtils.close();
            }
        }
    }

    /**
     * 获取所有topic的配置信息
     * kafka-configs.sh --zookeeper localhost:2181 --entity-type topics --describe
     */
    public static void listTopicAllConfig() {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply("localhost:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
            Map<String, Properties> configs = JavaConversions.mapAsJavaMap(AdminUtils.fetchAllTopicConfigs(zkUtils));
            // 获取特定topic的配置信息
            Properties properties = AdminUtils.fetchEntityConfig(zkUtils, "topics", "kafka-test");
            for (Map.Entry<String, Properties> entry : configs.entrySet()) {
                System.out.println("key=" + entry.getKey() + " ;value= " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zkUtils != null) {
                zkUtils.close();
            }
        }
    }
}
