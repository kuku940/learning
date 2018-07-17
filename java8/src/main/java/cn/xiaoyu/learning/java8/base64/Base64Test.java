package cn.xiaoyu.learning.java8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * Base64编码
 * Base64工具类提供静态方法获取下面三种编码解码：
 * 1. 基本   - 输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
 * 2. URL   - 输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
 * 3. MIME  - 输出映射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
 */
public class Base64Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 基本编码器
        String str = "java8";
        String encode = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        byte[] bytes = Base64.getDecoder().decode(encode);
        String decode = new String(bytes, "utf-8");
        System.out.println(str + " -> " + encode + " -> " + decode);

        // URL编码器
        String url = "https://www.baidu.com/s?wd=%E4%B8%AD%E5%9B%BD";
        encode = Base64.getUrlEncoder().encodeToString(url.getBytes("UTF-8"));
        bytes = Base64.getUrlDecoder().decode(encode);
        decode = new String(bytes, "utf-8");
        System.out.println(url + " -> " + encode + " -> " + decode);


        // MIME
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
        }

        byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println(mimeEncodedString);
    }
}
