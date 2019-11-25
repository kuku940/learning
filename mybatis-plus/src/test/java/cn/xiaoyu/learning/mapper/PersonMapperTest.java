package cn.xiaoyu.learning.mapper;

import cn.xiaoyu.learning.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void testSelect() {
        List<User> list = mapper.selectList(null);
        list.forEach(user -> System.out.println(user.getName()));

        Map map = new HashMap();
        map.put("name", "jack");
        List<User> l = mapper.selectByMap(map);
        l.forEach(user -> System.out.println(user.toString()));

        System.out.println("------------分页插件------------");
        Page page = new Page<User>(2, 1);
        IPage<User> users = mapper.selectPageVo(page);
        users.getRecords().forEach(user -> System.out.println(user.toString()));
    }
}
