import cn.xiaoyu.learning.data.jooq.Tables;
import cn.xiaoyu.learning.data.jooq.tables.pojos.User;
import cn.xiaoyu.learning.data.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.DriverManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JooqTest {
    private DSLContext dslContext;

    @Before
    public void before() {
        this.dslContext = getDSLContext();
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("Jack");
        user.setAge(21);
        user.setEmail("jack@ali.com");
        UserRecord userRecord = dslContext.newRecord(Tables.USER, user);
        int i = userRecord.store();
        Assert.assertEquals(1, i);
    }

    @Test
    public void select() {
        Result<Record> result = dslContext.select().from(Tables.USER).fetch();
        result.get(0);
    }

    @Test
    public void trans() {
        try {
            dslContext.transaction(configuration -> {
                User user = new User(null, "xiaoming", 26, "xiaoming@sql.cn");
                UserRecord userRecord = DSL.using(configuration).newRecord(Tables.USER, user);
                Assert.assertEquals(1, userRecord.store());

                throw new RuntimeException("mock an exception");
            });
        } catch (Exception e) {

        }
    }

    @Test
    public void delete() {
        dslContext.delete(Tables.USER)
                .where(Tables.USER.NAME.eq("Jack"))
                .execute();

    }

    private DSLContext getDSLContext() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/learning?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT", "root", "123456");
            return DSL.using(connection, SQLDialect.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}