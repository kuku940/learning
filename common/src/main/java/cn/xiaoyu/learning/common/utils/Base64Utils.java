package cn.xiaoyu.learning.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Base64 加密解密
 *
 * @author Roin zhang
 * @date 2018/8/22
 */

public class Base64Utils {
    /**
     * 字节数组转Base64编码
     *
     * @param bytes 需要加密的字节数组
     * @return Base64加密后的字节数组
     */
    public static String byte2Base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * Base64编码转字节数组
     *
     * @param base64Key 需要解密的字节数组
     * @return Base64解密后的字节数组
     */
    public static byte[] base642Byte(String base64Key) {
        return Base64.decodeBase64(base64Key);
    }
}
