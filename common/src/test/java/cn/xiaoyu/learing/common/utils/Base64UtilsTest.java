package cn.xiaoyu.learing.common.utils;

import cn.xiaoyu.learning.common.utils.Base64Utils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Base64 加密测试类
 *
 * @author Roin zhang
 * @date 2018/8/22
 */

public class Base64UtilsTest {
    @Test
    public void test() throws UnsupportedEncodingException {
        String s = Base64Utils.byte2Base64("辽宁号 舷号16".getBytes("UTF-8"));
        System.out.println(s);

        byte[] bytes = Base64Utils.base642Byte(s);
        System.out.println(new String(bytes));
    }
}
