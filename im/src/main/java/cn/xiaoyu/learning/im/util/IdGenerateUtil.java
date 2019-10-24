package cn.xiaoyu.learning.im.util;

import java.util.UUID;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class IdGenerateUtil {
    /**
     * @return 随机用户Id
     */
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
