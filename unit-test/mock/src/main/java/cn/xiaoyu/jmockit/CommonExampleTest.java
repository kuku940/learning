package cn.xiaoyu.jmockit;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 这是个标准的注入以及测试方式
 * <p>
 * 针对UserDao这个对象可以手动new一个对象，然后再通过设置私有变量的方式注入进去
 * 然后UserService初始化也是通过new对象的方式，然后直接调用，而不是@Tested这种标准写法
 * 这样的话，可以将controller-service-dao层整体写完，一个单元测试，跑通三个类
 *
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
                System.out.println("insert user");
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
