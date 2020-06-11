package cn.xiaoyu.mockito.service;

import cn.xiaoyu.mockito.dao.UserDao;
import cn.xiaoyu.mockito.entity.User;

import java.util.List;

/**
 * @author roin.zhang
 * @date 2020/6/11
 */
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(Long userId) {
        List<User> users = userDao.getUsers(userId);
        return users != null && !users.isEmpty();
    }

    public void regist(User user) {
        userDao.insertUser(user);
    }
}
