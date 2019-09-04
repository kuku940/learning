package cn.xiaoyu.learning.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by admin on 2016/10/2.
 */
public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        // 声明并启动服务器
        new EchoServer(8889).start();
    }

    private void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 配置服务器NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建ServerBootstrap对象，Netty用于启动NIO服务器的辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置并绑定Reactor线程池，处理所有注册到本线程多路复用器Selector的channel
            bootstrap.group(bossGroup, workerGroup)
                    // 设置并绑定服务器Channel
                    .channel(NioServerSocketChannel.class)
                    //指定内核为此套接接口排队的最大连接数
                    .option(ChannelOption.SO_BACKLOG, 100)
                    // 为辅助类和其父类分别指定handler
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 链路的创建并初始化ChannelPipeline，负责处理网络时间的职责链，复制管理和执行ChannelHandler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 初始化ChannelPipeline后，添加并设置ChannelHandler
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //添加LineBasedFrameDecoder和StringDecoder解码器
                            // 第一个参数1024表示单条记录的最大长度，当达到这个长度后会抛出TooLoogFrameException异常
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());

                            //将ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            //异步地绑定服务器并监听端口，sync()同步等待绑定完成
            ChannelFuture future = bootstrap.bind(port).sync();
            //获得这个Channel的closeFuture，阻塞当前线程直到关闭操作完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopgroup，释放所有资源
            workerGroup.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }
}
