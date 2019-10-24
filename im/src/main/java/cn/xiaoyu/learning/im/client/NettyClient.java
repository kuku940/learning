package cn.xiaoyu.learning.im.client;

import cn.xiaoyu.learning.common.ThreadPoolManager;
import cn.xiaoyu.learning.im.client.handler.LoginResponseHandler;
import cn.xiaoyu.learning.im.client.handler.MessageResponseHandler;
import cn.xiaoyu.learning.im.codec.PacketDecoder;
import cn.xiaoyu.learning.im.codec.PacketEncoder;
import cn.xiaoyu.learning.im.codec.Spliter;
import cn.xiaoyu.learning.im.protocol.request.LoginRequestPacket;
import cn.xiaoyu.learning.im.protocol.request.MessageRequestPacket;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Roin zhang
 * @date 2019/9/21
 */
public class NettyClient {
    private static final Log LOGGER = LogFactory.getLog(NettyClient.class);
    private static final int MAX_RETRY = 3;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                // 给NioSocketChannel绑定自定义属性
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        LOGGER.info(ch.attr(AttributeKey.valueOf("clientName")) + "启动成功");
                        // 自定义拆包处理器 + 是否支持自定义协议的客户端
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                })
                // 设置TCP底层相关属性
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true);

        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    private static void connect(final Bootstrap bootstrap, final String host, final int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                LOGGER.info("客户端连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                LOGGER.info("重试次数已用完，放弃连接");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                LOGGER.info(new Date() + "连接失败，第" + order + "次重连");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), 1 << order, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        ThreadPoolManager.getInstance().submit(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    LOGGER.info("要发送用户userId:");
                    String toUserId = sc.nextLine();
                    LOGGER.info("需要发送消息：");
                    String msg = sc.nextLine();

                    channel.writeAndFlush(new MessageRequestPacket(toUserId, msg));
                } else {
                    LOGGER.info("输入用户名登录：");
                    String username = sc.nextLine();
                    loginRequestPacket.setUsername(username);
                    loginRequestPacket.setPassword("123456");

                    // 发送登录数据包
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                }
            }
        });
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
