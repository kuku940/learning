package cn.xiaoyu.jmockit;

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

import java.util.Calendar;

import static org.mockito.Matchers.anyLong;

/**
 * @author roin.zhang
 * @date 2020/6/11
 */
@RunWith(JMockit.class)
public class CommonExampleTest {
    @Tested
    UserService userService;

    @Before
    public void init() {
    }

    @Test
    public void testLogin(@Injectable UserDao userDao) {
        User user = new User();
        new Expectations() {{
            userDao.get(withInstanceOf(Long.class));
            returns(user, null);
            times = 2;
        }};

        Assert.assertTrue(userService.login(1L));
        Assert.assertFalse(userService.login(1L));
    }

    @Test
    public void testInsert(@Injectable UserDao userDao) {
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

}

class User {
    public Long userId;
}

interface UserDao {
    User get(Long userId);

    void insert(User user);
}

class UserService {
    private UserDao userDao;

    public boolean login(Long userId) {
        User user = userDao.get(userId);
        return user != null;
    }

    public void regist(User user) {
        userDao.insert(user);
    }
}
