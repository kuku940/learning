package cn.xiaoyu.learning.kafka.simple;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 通过JavaApi来创建topic
 * 引入kafka-core包
 */
public class SimpleTopicOperate {
    public static void main(String[] args) {
        ZkUtils zkUtils = ZkUtils.apply("localhost:2181", 3000, 3000, JaasUtils.isZkSecurityEnabled());
        // 创建topic
        AdminUtils.createTopic(zkUtils, "topic.t1", 1, 3, new Properties(), RackAwareMode.Enforced$.MODULE$);

        // 查询Topic
        Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), "topic.t1");
        Iterator it = props.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "=" + value);
        }

        // 修改Topic
        props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), "topic.t1");
        props.put("min.cleanable.dirty.ratio", "0.3");
        props.remove("max.message.bytes");

        AdminUtils.changeTopicConfig(zkUtils, "topic.t1", props);

        // 删除Topic
        AdminUtils.deleteTopic(zkUtils, "topic.t1");

        zkUtils.close();
    }
}
