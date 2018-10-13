package cn.xiaoyu.learing.common.utils;

import cn.xiaoyu.learning.common.utils.GZipUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author Roin zhang
 * @date 2018/8/22
 */

public class GZipUtilsTest {
    @Test
    public void test() throws UnsupportedEncodingException {
        String str = "愿你的恶名从爱尔兰到契丹无人不知无人不晓";

        byte[] bytes = str.getBytes("UTF-8");
        System.out.println("压缩前：" + bytes.length);
        byte[] compress = GZipUtils.compress(bytes);
        System.out.println("压缩后：" + compress.length);

        byte[] uncompress = GZipUtils.uncompress(compress);
        System.out.println("解压后：" + uncompress.length);
        System.out.println(new String(uncompress));
    }
}
