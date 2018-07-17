package cn.xiaoyu.guava.string;

import com.google.common.base.CharMatcher;

/**
 * CharMatcher提供了各种方法来处理各种Java char类型值
 */

public class CharMatcherTest {
    public static void main(String[] args) {
        // 提取数字
        System.out.println(CharMatcher.DIGIT.retainFrom("mahesh123"));
        // 替换空白字符为' '
        System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom("     Mahessh     Parashar", ' '));

        // 替换数字为*号
        System.out.println(CharMatcher.JAVA_DIGIT.replaceFrom("mahesh123", "*"));
        // 提取数字和小写字母
        System.out.println(CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom("MAhesh123"));

    }
}
