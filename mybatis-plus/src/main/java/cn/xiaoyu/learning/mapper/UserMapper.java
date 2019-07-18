package cn.xiaoyu.learning.mapper;

import cn.xiaoyu.learning.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Roin
 * @since 2019-07-17
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectPageVo(Page page);
}
