package cn.xiaoyu.learning.socket;

import cn.xiaoyu.learning.common.ThreadPoolManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2016/10/1.
 * socket编程中server服务器端
 * 同步阻塞IO服务器
 */
public class Server {
    public static void main(String[] args) throws IOException {
        int port = 8899;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("服务器启动成功...");
            ExecutorService executor = ThreadPoolManager.getInstance();
            while (true) {
                Socket socket = server.accept();
                // 启动新线程处理这次请求
                executor.execute(new Task(socket));
            }
        }
    }
}

