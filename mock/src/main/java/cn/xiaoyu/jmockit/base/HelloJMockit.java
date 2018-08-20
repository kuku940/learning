package cn.xiaoyu.jmockit.base;

import java.util.Locale;

/**
 * @author Roin zhang
 * @date 2018/7/25
 */

public class HelloJMockit {
    /**
     * 向JMockit打招呼
     *
     * @return
     */
    public String sayHello() {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            // 在中国，就说中文
            return "你好，JMockit!";
        } else {
            // 在其它国家，就说英文
            return "Hello，JMockit!";
        }
    }
}