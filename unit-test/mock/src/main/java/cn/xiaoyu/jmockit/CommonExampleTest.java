package cn.xiaoyu.jmockit;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.mockito.Matchers.anyLong;

/**
 * @author roin.zhang
 * @date 2020/6/11
 */
@RunWith(JMockit.class)
public class CommonExampleTest {
    @Injectable
    UserDao userDao;
    @Tested
    UserService userService;

    @Before
    public void init() {
    }

    @Test
    public void testLogin() {
        User user = new User();
        new Expectations() {{
            userDao.get(withInstanceOf(Long.class));
            returns(user, null);
            times = 2;
            // mock无返回值的
            userDao.insert(withInstanceOf(User.class));
            times = 1;
        }};

        Assert.assertTrue(userService.login(1L));
        Assert.assertFalse(userService.login(1L));

        userService.regist(new User());
        new Verifications() {{
            userDao.insert((User) any);
            times = 1;
        }};
    }

    @Test
    public void testGetValue() {
        // 设置私有成员变量的值
        Deencapsulation.setField(userService, "value", "5");
        Assert.assertEquals("5", userService.getValue());
    }

    @Test
    public void testInsert() {
        new MockUp<UserDao>(UserDao.class) {
            @Mock
            public void insert(User user) {
            }
        };

        userService.regist(new User());
        new Verifications() {{
            userDao.insert((User) any);
            times = 1;
        }};
    }

    @Test
    public void testDoGetSession2() {
        new MockUp<CommonService>(CommonService.class) {
            @Mock
            public String getSessionId() {
                return "123456";
            }
        };

        String session = userService.doGetSession();
        Assert.assertEquals("123456", session);
    }

}

class User {
    public Long userId;
}

interface UserDao {
    <T> User get(T userId);

    void insert(User user);
}

class CommonService {
    public String getSessionId() {
        return "sessionId";
    }
}

class UserService extends CommonService {
    private UserDao userDao;
    private String value;

    public boolean login(Long userId) {
        User user = userDao.get(userId);
        return user != null;
    }

    public String getValue() {
        return this.value;
    }

    public String doGetSession() {
        return this.getSessionId();
    }

    public void regist(User user) {
        userDao.insert(user);
    }
}
