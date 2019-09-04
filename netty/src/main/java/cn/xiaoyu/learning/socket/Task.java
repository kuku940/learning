package cn.xiaoyu.learning.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by Administrator on 2016/10/8.
 * 新起一个线程来完成任务
 */
public class Task implements Runnable {
    private Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handerSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handerSocket() throws Exception {
        // 这儿设置的编码要和客户端的编码保持一致这样
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
        StringBuilder sb = new StringBuilder();
        String temp;
        int index;
        while ((temp = br.readLine()) != null) {
            // 读到eof的时候就结束接收
            if ((index = temp.indexOf("eof")) != -1) {
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }
        System.out.println("from client:" + sb);

        // 往客户端写入一段话
        Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        writer.write("Hello Client.");
        writer.write("eof\n");
        writer.flush();

        writer.close();
        br.close();
        socket.close();
    }
}