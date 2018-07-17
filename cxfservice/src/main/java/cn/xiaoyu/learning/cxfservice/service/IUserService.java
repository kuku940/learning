package cn.xiaoyu.learning.cxfservice.service;

import cn.xiaoyu.learning.cxfservice.domain.User;

import javax.jws.WebService;

/**
 * @author Roin
 * @Date 2018-07-06
 */
@WebService
public interface IUserService {
    /**
     * 获取个人信息
     *
     * @param userId
     * @return
     */
    User getUserById(long userId);

    String getName(String username);
}
