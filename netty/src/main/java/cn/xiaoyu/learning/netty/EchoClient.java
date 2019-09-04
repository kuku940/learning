package cn.xiaoyu.learning.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2016/10/2.
 */
public class EchoClient {
    private String host;
    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("127.0.0.1", 8889).start();
    }

    public void start() throws Exception {
        // 配置客户端NIO线程组,可通过构造方法指定io线程数，默认为内核数2倍
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            // 指定EventLoopgroup来处理客户端时间，需要EventLoopgroup的NIO实现
            bootstrap.group(workGroup)
                    // 用于NIO传输的Channel类型
                    .channel(NioSocketChannel.class)
                    // 设置服务器的 InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host, port))
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    // 当一个Channel创建时，把一个EchoClinet加入他的pipeline中
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //添加LineBasedFrameDecoder和StringDecoder解码器
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());

                            //将ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件
                            socketChannel.pipeline().addLast(new EchoClientHandler(100));
                        }
                    });
            // 连接到远程;等待连接完成
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            // 关闭连接池，释放所有资源
            future.channel().closeFuture().sync();
        } finally {
            // 调用 shutdownGracefully() 来关闭线程池和释放所有资源
            workGroup.shutdownGracefully().sync();
        }
    }
}
