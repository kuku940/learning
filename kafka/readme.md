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
    
###  