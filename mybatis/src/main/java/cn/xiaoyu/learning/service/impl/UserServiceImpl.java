package cn.xiaoyu.learning.service.impl;

import cn.xiaoyu.learning.entity.User;
import cn.xiaoyu.learning.mapper.UserMapper;
import cn.xiaoyu.learning.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Roin
 * @since 2019-07-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
