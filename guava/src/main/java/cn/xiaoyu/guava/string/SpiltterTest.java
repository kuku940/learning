package cn.xiaoyu.guava.string;

import com.google.common.base.Splitter;

/**
 * Splitter提供各种方法来处理分割操作字符串
 */

public class SpiltterTest {
    public static void main(String[] args) {
        System.out.println(Splitter.on("-").trimResults().omitEmptyStrings()
                .split("the-quick-brown-fox-jumps-over-the-lazy-little-dog."));
    }
}
