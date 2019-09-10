package cn.xiaoyu.learning.common.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.*;

/**
 * @author roin.zhang
 * @date 2019-09-10
 * @link {https://www.twblogs.net/a/5b81d9212b71772165ae977a/zh-cn}
 */
public class JDBCPool {
    private static final String URL = "jdbc:mysql://localhost:3306/learning";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private volatile static JDBCPool pool;

    public static JDBCPool getInstance() {
        if (pool == null) {
            synchronized (JDBCPool.class) {
                if (pool == null) {
                    pool = new JDBCPool();
                }
            }
        }
        return pool;
    }

    private static GenericObjectPool<Connection> connPool;

    private JDBCPool() {
        connPool = new GenericObjectPool<>(new JDBCPooledFactory(), getDefaultConfig());
    }

    public JDBCPool(GenericObjectPoolConfig config) {
        if (config == null) {
            config = getDefaultConfig();
        }
        connPool = new GenericObjectPool<>(new JDBCPooledFactory(),
                config);
    }

    private GenericObjectPoolConfig getDefaultConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(50);
        config.setMinIdle(0);
        config.setMaxWaitMillis(60000);
        return config;
    }

    public GenericObjectPool<Connection> getConnectionPool() {
        return connPool;
    }

    public Connection getConnection() throws Exception {
        return getConnectionPool().borrowObject();
    }

    public static void closePsAndRs(PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                rs = null;
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                ps = null;
            }
        }
    }

    public static void returnConnection(Connection conn) {
        connPool.returnObject(conn);
    }

    public static void returnConnectionAndClose(Connection conn, PreparedStatement ps, ResultSet rs) {
        closePsAndRs(ps, rs);
        returnConnection(conn);
    }

    static class JDBCPooledFactory extends BasePooledObjectFactory<Connection> {
        static {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Connection create() throws Exception {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        @Override
        public PooledObject<Connection> wrap(Connection connection) {
            return new DefaultPooledObject<>(connection);
        }
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 200; i++) {
//               // 1 单线程不归还连接
//               Connection conn = JDBCPool.getInstance().getConnection();
//               System.out.println(conn.hashCode());
//               // 2 单线程归还连接
//               JDBCPool.returnConnection(conn);
                // 3 多线程不归还
                new Thread(new PoolTestThread()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class PoolTestThread implements Runnable {

        @Override
        public void run() {
            try {
                Connection conn = JDBCPool.getInstance().getConnection();
                System.out.println(Thread.currentThread().getName() + " : "
                        + conn.hashCode());
                // 4 多线程归还连接
                Thread.sleep(1000);
                JDBCPool.returnConnection(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
