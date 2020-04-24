package cn.xiaoyu.test.container;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.shaded.com.google.common.net.HttpHeaders;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author roin.zhang
 * @link {https://www.testcontainers.org/}
 * @date 2019/10/22
 */
public class RedisServerTest {
    /**
     * 创建并启动容器，并且暴露端口号
     */
    @ClassRule
    public static GenericContainer redis = new GenericContainer("redis:5.0.5").withExposedPorts(6379);
    @Rule
    public KafkaContainer kafka = new KafkaContainer();
    @Rule
    public MySQLContainer mysql = new MySQLContainer();
    @Rule
    public MockServerContainer mockServer = new MockServerContainer();

    @Test
    public void testRedisInsert() {
        try (Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379))) {
            jedis.set("name", "jack");
            Assert.assertEquals("jack", jedis.get("name"));
        }
    }

    @Test
    public void testMysql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword());
        Assert.assertNotNull(conn);
    }

    @Test
    public void testMockServer() {
        String endpoint = mockServer.getEndpoint();
        Assert.assertNotNull(endpoint);

        try (MockServerClient mockServerClient = new MockServerClient(mockServer.getContainerIpAddress(), mockServer.getServerPort())) {
            mockServerClient.when(request().withPath("/person").withQueryStringParameter("name", "peter"))
                    .respond(response().withBody("Peter the person!"));
            mockServerClient.when(HttpRequest.request().withPath("/update_issue_bill_status").withMethod("POST")
                    .withHeader(HttpHeaders.CONTENT_TYPE, "application/x-protobuf"))
                    .respond(HttpResponse.response().withStatusCode(200)
                            .withHeader(new Header(HttpHeaders.CONTENT_TYPE, "application/x-protobuf"))
                            // 可以是对象转成字节数组
                            .withBody("hello".getBytes())
                    );
        }

    }
}
