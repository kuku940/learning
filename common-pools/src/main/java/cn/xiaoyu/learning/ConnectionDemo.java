package cn.xiaoyu.learning;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author roin.zhang
 * @date 2020/3/26
 */
public class ConnectionDemo {
    public static void main(String[] args) throws SQLException {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(5);
        config.setMinIdle(2);
        config.setMaxWaitMillis(200);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);

        DatasourcePool dbpools = new DatasourcePool(new ConnectionFactory(), config);

        for (int i = 0; i < 20; i++) {
            Connection connection = dbpools.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("insert into t_person(name, age) values ('a', 20)");
            dbpools.returnConnection(connection);
        }
    }
}
