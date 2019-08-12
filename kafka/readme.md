## kafka伪集群部署
kafka分别启动在9091，9092，9093三个端口下的broker

## 启动kafka

    1. 启动zookeeper集群
    2. 启动kafka集群
    
## 引入jar

    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-clients</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-streams</artifactId>
        <version>2.3.0</version>
    </dependency>

### Simple结构说明

    SimpleProducer  -   生产消息到topic.in
    SimpleStream    -   消费topic.in的消息转换为大写，并写入topic.out
    SimpleConsumer  -   消费SimpleStream写入到topic.out的消息
    
### kafka streams
kafka流处理，通过stream api来实现，开发流程如下：

    1. 配置kafka streams
    2. 创建Serde实例
    3. 创建处理的拓扑
    4. 创建和启动KStream
    
参考：  
```ZMartStreams类``` - 案例，不能运行  
```SimpleStreams类``` - 可运行，将消息转换为大写

### 自定义标签生成kafka消费者

component组件包中存在自定义注解；  
ConsumerAnnoTest包含自定义标签形式消费的核心逻辑
SimpleAnnoConsumer