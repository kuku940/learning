package cn.xiaoyu.learning.netty.messagepack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

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
        new EchoClient("localhost", 6888).start();
    }

    public void start() throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            // 指定EventLoopgroup来处理客户端时间，需要EventLoopgroup的NIO实现
            bootstrap.group(workGroup)
                    // 用于NIO传输的Channel类型
                    .channel(NioSocketChannel.class)
                    // 设置服务器的InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 当一个Channel创建时，把一个EchoClinet加入他的pipeline中
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            socketChannel.pipeline().addLast("msg pack decoder", new MsgpackDecoder());
                            socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            socketChannel.pipeline().addLast("msg pack encoder", new MsgpackEncoder());

                            //将ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            // 链接到远端，一直等到链接完成
            ChannelFuture future = bootstrap.connect().sync();
            // 关闭连接池，释放所有资源
            future.channel().closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully().sync();
        }
    }
}
