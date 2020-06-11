package cn.xiaoyu.mockito;

import cn.xiaoyu.mockito.dao.UserDao;
import cn.xiaoyu.mockito.entity.User;
import cn.xiaoyu.mockito.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author roin.zhang
 * @date 2020/6/11
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMockito {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() {
        List<User> users = new ArrayList() {{
            add(new User() {{
                setName("zhangsan");
            }});
        }};
        when(userDao.getUsers(anyLong())).thenReturn(users, null);

        Assert.assertTrue(userService.login(1L));
        Assert.assertFalse(userService.login(2L));
    }

    @Test
    public void testInsert() {
        doNothing().when(userDao).insertUser(any(User.class));
        userService.regist(new User());
    }
}
