package cn.xiaoyu.guava.string;

import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * Joiner提供了各种方法来处理字符串加入操作，对象等
 */

public class JoinerTest {
    public static void main(String[] args) {
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,4,5,null,6)));
    }
}
