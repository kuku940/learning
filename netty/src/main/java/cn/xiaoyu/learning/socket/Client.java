package cn.xiaoyu.learning.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2016/10/1.
 * socket编程中client客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8899;
        Socket client = new Socket(host, port);
        // 设置超时时间
        client.setSoTimeout(15000);

        Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");
        writer.write("你好，服务器");
        writer.write("eof\n");
        writer.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String temp;
        int index;
        while ((temp = br.readLine()) != null) {
            if ((index = temp.indexOf("eof")) != -1) {
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }
        System.out.println("from server:" + sb);
        writer.close();
        br.close();
        client.close();
    }
}
