package cn.xiaoyu.learning.netty.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2016/10/2.
 */
public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        // 声明并启动服务器
        new EchoServer(6888).start();
    }

    private void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 配置服务器NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建ServerBootstrap对象，Netty用于启动NIO服务器的辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    // 指定使用一个NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    //指定端口设置的socket地址
                    .localAddress(new InetSocketAddress(port))
                    // 在Channel的ChannelPipeline中加入EchoServerHandler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            socketChannel.pipeline().addLast("msg pack decoder", new MsgpackDecoder());
                            socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            socketChannel.pipeline().addLast("msg pack encoder", new MsgpackEncoder());

                            //将ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            //异步地绑定服务器，sync()同步等待绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            //获得这个Channel的closeFuture，阻塞当前线程直到关闭操作完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopgroup，释放所有资源
            workerGroup.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }
}
