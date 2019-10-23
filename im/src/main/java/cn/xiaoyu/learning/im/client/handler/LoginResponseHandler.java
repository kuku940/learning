package cn.xiaoyu.learning.im.client.handler;

import cn.xiaoyu.learning.im.protocol.request.LoginRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.LoginResponsePacket;
import cn.xiaoyu.learning.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.UUID;

/**
 * @author roin.zhang
 * @date 2019/10/23
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    private static final Log LOGGER = LogFactory.getLog(LoginResponseHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info(new Date() + ": 客户端客户端开始登陆");

        // 构建登陆对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("jack");
        loginRequestPacket.setPassword("123456");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        if (loginResponsePacket.isSuccess()) {
            LOGGER.info(new Date() + ": 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            LOGGER.info(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("客户端连接被关闭");
    }
}
