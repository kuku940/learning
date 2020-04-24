package cn.xiaoyu.learning.service;

import cn.xiaoyu.learning.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务类
 *
 * @author Roin
 * @since 2019-07-17
 */
public interface IUserService extends IService<User> {
    IPage<User> selectUserByPage(Page<User> page);
}
