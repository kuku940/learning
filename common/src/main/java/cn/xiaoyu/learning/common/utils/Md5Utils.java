package cn.xiaoyu.learning.common.utils;

import cn.xiaoyu.learning.common.exception.BizException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author Roin zhang
 * @date 2018/8/17
 */

public class Md5Utils {
    /**
     * MD5加密
     *
     * @param context  要加密的文本
     * @param encoding 编码格式
     * @return md5加密后文本
     */
    public static String md5Encryption(String context, String encoding) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes(encoding));
            byte[] byteHash = md.digest();
            StringBuffer sTemp = new StringBuffer();
            int i;
            for (int offset = 0; offset < byteHash.length; offset++) {
                i = byteHash[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sTemp.append("0");
                }
                sTemp.append(Integer.toHexString(i));
            }

            return sTemp.toString();
        } catch (Exception e) {
            throw new BizException(e.getMessage(), e);
        }
    }

    public static String md5(String key, String encoding) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes(encoding);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new BizException(e.getMessage(), e);
        }
    }

    /**
     * MD5加密
     */
    public static String md5Encoding(String key, String encoding) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(key.getBytes(encoding));
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new BizException(e.getMessage(), e);
        }
    }


    /**
     * MD5加盐
     * 每次保存密码到数据库时，都生成一个随机16位数字，将这16位数字和密码相加再求MD5摘要，然后在摘要中再将这16位数字按规则掺入形成一个48位的字符串。
     * 在验证密码时再从48位字符串中按规则提取16位数字，和用户输入的密码相加再MD5。按照这种方法形成的结果肯定是不可直接反查的，且同一个密码每次保存时形成的摘要也都是不同的。
     */
    public static String md5WithSalt(String key, String encoding) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        key = md5Encoding(key + salt, encoding);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = key.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = key.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 校验密码是否正确
     */
    public static boolean verify(String key, String md5, String encoding) {
        char[] pwdArr = new char[32];
        char[] saltArr = new char[16];
        for (int i = 0; i < 48; i += 3) {
            pwdArr[i / 3 * 2] = md5.charAt(i);
            pwdArr[i / 3 * 2 + 1] = md5.charAt(i + 2);
            saltArr[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(saltArr);
        return md5Encoding(key + salt, encoding).equals(new String(pwdArr));
    }
}
