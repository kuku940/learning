package cn.xiaoyu.learning;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 创建池化对象 - 定义生成对象、激活对象、钝化对象、销毁对象等方法
 *
 * @author roin.zhang
 * @date 2020/3/26
 */
public class ConnectionFactory extends BasePooledObjectFactory<Connection> {
    private static final String DRIVER = "com.mysql.jdbc.Dirver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    /**
     * 创建新的对象
     */
    @Override
    public Connection create() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    /**
     * 使用PooledObject对数据库连接进行包装
     */
    @Override
    public PooledObject<Connection> wrap(Connection connection) {
        return new DefaultPooledObject<>(connection);
    }

    /**
     * 于validateObject失败或其它什么原因，将对象实例从对象池中移除
     * 不能保证对象实例被移除时所处的状态
     */
    @Override
    public void destroyObject(PooledObject<Connection> pooledConnection) throws Exception {
        Connection connection = pooledConnection.getObject();
        connection.close();
    }

    /**
     * 仅能被active状态的对象实例调用
     * 从对象池获取对象实例，在对象池返回该对象实例前，调用该方法校验其状态
     * 对象实例归还给对象池时，在调用passivateObject方法前，使用该方法校验其状态
     */
    @Override
    public boolean validateObject(PooledObject<Connection> p) {
        Connection connection = p.getObject();
        try {
            return connection.isValid(1);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对象实例在归还给对象池时调用了passivateObject方法，通过对象池再次取到该对象实例
     * 在对象池返回该对象实例前，需要调用该方法
     */
    @Override
    public void activateObject(PooledObject<Connection> p) throws Exception {
        super.activateObject(p);
    }

    /**
     * 对象实例归还给对象池时调用
     */
    @Override
    public void passivateObject(PooledObject<Connection> p) throws Exception {
        super.passivateObject(p);
    }
}
