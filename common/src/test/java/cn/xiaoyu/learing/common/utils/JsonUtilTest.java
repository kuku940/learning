package cn.xiaoyu.learing.common.utils;

import cn.xiaoyu.learning.common.domain.IBean;
import cn.xiaoyu.learning.common.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtilTest {
    @Test
    public void test() throws Exception {
        IBean bean = new IBean() {{
            setId(1L);
            setCreateUser("123456");
            setLastUpdateUser("123456");
        }};

        List<IBean> beans = new ArrayList() {{
            add(bean);
        }};

        Map<String, IBean> beanMap = new HashMap() {{
            put("A", bean);
        }};
        // 转对象
        String benaJson = JsonUtils.writeObject2JSON(bean);
        IBean bean1 = (IBean) JsonUtils.writeJSON2Object(benaJson, IBean.class);

        Assert.assertEquals("123456", bean1.getCreateUser());

        // 转对象
        IBean bean2 = JsonUtils.writeJSON2Entity(benaJson, IBean.class);

        Assert.assertEquals("123456", bean2.getCreateUser());

        // 转List
        String beansJson = JsonUtils.writeObject2JSON(beans);
        List<IBean> beans2 = (List<IBean>) JsonUtils.writeJSON2Collection(beansJson, List.class, IBean.class);

        Assert.assertEquals("123456", beans2.get(0).getCreateUser());

        // 转Map
        String beanMapJson = JsonUtils.writeObject2JSON(beanMap);
        Map<String, IBean> beanMap2 = (Map<String, IBean>) JsonUtils.writeJSON2Collection(beanMapJson, Map.class, String.class, IBean.class);

        Assert.assertEquals("123456", beanMap2.get("A").getCreateUser());
    }
}
