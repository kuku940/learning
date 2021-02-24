package cn.xiaoyu.learing.common.utils;

import cn.xiaoyu.learning.common.utils.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilsTest {
    @Test
    public void test() {
        Demo demo = new Demo("上善若水", 25.012345678);
        List<Demo> list = new ArrayList() {{
            add(demo);
        }};

        System.out.println(demo);

        System.out.println(CollectionUtils.object2Map(demo));
        System.out.println(CollectionUtils.object2MapList(list));
    }
}
