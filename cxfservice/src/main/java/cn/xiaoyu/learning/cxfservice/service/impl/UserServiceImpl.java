package cn.xiaoyu.learning.cxfservice.service.impl;

import cn.xiaoyu.learning.cxfservice.domain.User;
import cn.xiaoyu.learning.cxfservice.service.IUserService;

import javax.jws.WebService;

@WebService
public class UserServiceImpl implements IUserService {
    @Override
    public User getUserById(long userId) {
        String[] habby = {"swimming", "gaming"};
        User user = new User(userId, "Roin", 25, habby);
        return user;
    }

    @Override
    public String getName(String username) {
        return username;
    }
}
