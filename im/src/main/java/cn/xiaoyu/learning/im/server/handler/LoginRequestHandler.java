package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.protocol.request.LoginRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.LoginResponsePacket;
import cn.xiaoyu.learning.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * 登录请求处理器
 *
 * @author roin.zhang
 * @date 2019/10/23
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(LoginRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LOGGER.info(new Date() + ": 客户端开始登录");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginRequestPacket.setVersion(loginRequestPacket.getVersion());
        if (vaild(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
            LOGGER.info(new Date() + ": 登录成功");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            LOGGER.info(new Date() + ": 登录失败");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean vaild(LoginRequestPacket loginRequestPacket) {
        return "jack".equals(loginRequestPacket.getUsername()) || "123456".equals(loginRequestPacket.getPassword());
    }
}
