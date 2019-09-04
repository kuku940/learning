package cn.xiaoyu.learning.socket;


import cn.xiaoyu.learning.common.ThreadPoolManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2016/10/8.
 * 伪异步IO服务器
 */
public class FakeServer {
    public static void main(String[] args) throws IOException {
        int port = 8899;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("伪异步IO服务器启动成功...");
            ExecutorService executor = ThreadPoolManager.getInstance();
            while (true) {
                Socket socket = server.accept();
                // 启动新线程处理这次请求
                executor.execute(new Task(socket));
            }
        }
    }
}
