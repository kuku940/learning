package cn.xiaoyu.netty.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Roin zhang
 * @date 2018/9/27
 */

public class EchoServer {
    private final int port = 8889;

    public static void main(String[] args) throws Exception {
        new EchoServer().start();
    }

    private void start() throws Exception {
        // 创建 EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建 ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    // 指定使用 NIO 的传输 Channel
                    .channel(NioServerSocketChannel.class)
                    // 设置 socket 地址使用所选的端口
                    .localAddress(new InetSocketAddress(port))
                    // 添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            // 绑定的服务器;sync 等待服务器关闭
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + "started and listen on "
                    + future.channel().localAddress());
            // 关闭 channel 和 块，直到它被关闭
            future.channel().closeFuture().sync();
        } finally {
            // 关机的 EventLoopGroup，释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
