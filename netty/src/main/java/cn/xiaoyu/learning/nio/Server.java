package cn.xiaoyu.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    // 选择器
    private Selector selector;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.initServer(6888);
        server.listen();
    }

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化工作
     *
     * @param port 监听的端口
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false); //设置通道为非阻塞
        serverChannel.socket().bind(new InetSocketAddress(port)); //将该通道对应的ServerSocke绑定到port端口上

        // 获得一个通道管理器
        this.selector = Selector.open();
        // 将通道管理器和该通道绑定，并该通道注册SelectionKey.OP_ACCEPT事件
        // 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     *
     * @throws IOException
     */
    public void listen() throws IOException {
        System.out.println("服务器端启动成功！");

        // 轮询访问Selector
        while (true) {
            // 当注册事件到达的时候方法返回，否则，该方法一直阻塞到有事件到达
            selector.select();

            // 获得selector中选中的项的迭代器，选中的项为注册事件
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //删除已选的key,以防止重复处理
                iterator.remove();

                // 客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);

                    //给客户端发送一条消息
                    channel.write(ByteBuffer.wrap(new String("向客户端发送一段消息").getBytes("utf-8")));

                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);
                }

                //读取客户端发送过来的信息
                else if (key.isReadable()) {
                    // 服务器可读取消息:得到事件发生的Socket通道
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 创建读取的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
//                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    int readBytes = channel.read(buffer);
                    byte[] data = buffer.array();
                    String msg = new String(data, "utf-8").trim();
                    System.out.println("服务端收到信息：" + msg);
                    ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
                    channel.write(outBuffer);// 将消息回送给客户端

                }
            }
        }
    }
}