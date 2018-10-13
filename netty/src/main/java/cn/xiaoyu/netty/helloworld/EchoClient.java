package cn.xiaoyu.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Roin zhang
 * @date 2018/9/27
 */

public class EchoClient {
    private final String host = "127.0.0.1";
    private final int port = 8889;

    public static void main(String[] args) throws Exception {
        new EchoClient().start();
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            // 指定 EventLoopGroup 来处理客户端事件。由于我们使用 NIO 传输，所以用到了 NioEventLoopGroup 的实现
            bootstrap.group(group)
                    // 使用的 channel 类型是一个用于 NIO 传输
                    .channel(NioSocketChannel.class)
                    // 设置服务器的 InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 当建立一个连接和一个新的通道时，创建添加到 EchoClientHandler 实例 到 channel pipeline
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            // 连接到远程;等待连接完成
            ChannelFuture future = bootstrap.connect().sync();
            // 阻塞直到 Channel 关闭
            future.channel().closeFuture().sync();
        } finally {
            // 调用 shutdownGracefully() 来关闭线程池和释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
