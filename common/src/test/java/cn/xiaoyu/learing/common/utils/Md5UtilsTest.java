package cn.xiaoyu.learing.common.utils;

import cn.xiaoyu.learning.common.utils.Md5Utils;
import org.junit.Test;

/**
 * @author Roin zhang
 * @date 2018/8/22
 */

public class Md5UtilsTest {
    @Test
    public void test() {
        String key = "Hello World!";
        String encoding = "UTF-8";
        System.out.println(Md5Utils.md5Encryption(key, encoding));
        System.out.println(Md5Utils.md5(key, encoding));
        System.out.println(Md5Utils.md5Encoding(key, encoding));

        String md5WithSalt = Md5Utils.md5WithSalt(key, encoding);
        System.out.println(md5WithSalt);
        System.out.println(Md5Utils.verify(key, md5WithSalt, encoding));


    }
}
