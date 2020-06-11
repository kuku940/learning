package cn.xiaoyu.mockito.dao;

import cn.xiaoyu.mockito.entity.User;

import java.util.List;

/**
 * @author roin.zhang
 * @date 2020/6/11
 */
public interface UserDao {
    List<User> getUsers(long userId);

    void insertUser(User user);
}
