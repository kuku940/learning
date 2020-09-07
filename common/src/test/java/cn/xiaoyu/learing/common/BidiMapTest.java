package cn.xiaoyu.learing.common;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 01399268
 * @date 2020/9/7
 */
public class BidiMapTest {
    @Test
    public void testBidiMap() {
        BidiMap<String, String> bidiMap = new TreeBidiMap<>();
        bidiMap.put("hello", "你好");

        Assert.assertEquals("你好", bidiMap.get("hello"));
        Assert.assertEquals("hello", bidiMap.getKey("你好"));
    }
}
