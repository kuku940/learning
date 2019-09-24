package cn.xiaoyu.learning.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 创建Netty服务端，必须指定三个属性：线程模型，IO模型以及连接读写处理逻辑
 *
 * @author Roin zhang
 * @date 2019/9/21
 */
public class NettyServer {
    private static final Log LOGGER = LogFactory.getLog(NettyServer.class);

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                // 给NioServerSocketChannel绑定自定义属性
                .attr(AttributeKey.newInstance("serverName"), "NettyServer")
                // 指定服务端启动过程中的逻辑
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        LOGGER.info(nioServerSocketChannel.attr(AttributeKey.valueOf("serverName")) + "服务启动中...");
                    }
                })
                // 给ChildHandler绑定自定义属性
                .childAttr(AttributeKey.newInstance("childName"), "ChildHandler")
                // 指定处理新连接数据的读写处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        LOGGER.info(AttributeKey.valueOf("childName") + "处理器处理中");
                        ch.pipeline().addLast(new ServerHandler());
                    }
                })
                // 设置TCP底层相关属性
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 设置三次握手请求队列的最大长度
                .option(ChannelOption.SO_BACKLOG, 1024);

        bind(serverBootstrap, 8000);
    }

    /**
     * 动态向上绑定端口，如果8000端口被占用，一直向上绑定
     *
     * @param serverBootstrap
     * @param port
     */
    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                LOGGER.info("端口[" + port + "]绑定成功");
            } else {
                LOGGER.info("端口[" + port + "]绑定失败");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
