package cn.xiaoyu.guava.string;

import com.google.common.base.CaseFormat;

/**
 * CaseFormat可以提供不同的ASCII字符串格式之间的转换
 * <p>
 * LOWER_CAMEL - Java变量的命名规则，如“lowerCamel”。
 * LOWER_HYPHEN - 连字符连接变量的命名规则，如“lower-hyphen”。
 * LOWER_UNDERSCORE - C++变量命名规则，如“lower_underscore”。
 * UPPER_CAMEL - Java和C++类的命名规则，如“UpperCamel”。
 * UPPER_UNDERSCORE - Java和C++常量的命名规则，如“UPPER_UNDERSCORE”。
 */

public class CaseFormatTest {
    public static void main(String[] args) {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test-data"));
    }
}
