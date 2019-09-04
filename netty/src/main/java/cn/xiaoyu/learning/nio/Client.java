package cn.xiaoyu.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {
    // 选择器
    private Selector selector;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.initClint("localhost", 6888);
        client.listen();
    }

    /**
     * 获得一个Socket通道，并对该通道做一些初始化的工作
     *
     * @param ip   服务器地址
     * @param port 服务器监听的端口
     * @throws IOException
     */
    public void initClint(String ip, int port) throws IOException {
        // 获得一个Socket通道
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);  //设置为非阻塞模式

        //打开一个选择器
        this.selector = Selector.open();

        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen()方法中调
        // 用channel.finishConnect();才能完成连接
        channel.connect(new InetSocketAddress(ip, port));
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     *
     * @throws IOException
     */
    public void listen() throws IOException {
        while (true) {
            this.selector.select();

            // 获得selector中选中的项的迭代器
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                iterator.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }

                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("向服务端发送了第一条信息").getBytes("utf-8")));

                    channel.register(this.selector, SelectionKey.OP_READ);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    channel.write(ByteBuffer.wrap(new String("向服务端发送了第二条信息").getBytes("utf-8")));
                    channel.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //客户器可读取消息:得到事件发生的Socket通道
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 创建读取的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    channel.read(buffer);
                    byte[] data = buffer.array();
                    String msg = new String(data, "utf-8").trim();
                    System.out.println("客户端收到信息：" + msg);

                    System.out.println("--------------------------");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }

                    ByteBuffer outBuffer = ByteBuffer.wrap("客户端收到消息".getBytes());
                    channel.write(outBuffer);// 将消息回送给服务端端
                    System.out.println("==========================");
                }
            }
        }
    }
}
