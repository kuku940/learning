package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.common.Session;
import cn.xiaoyu.learning.im.protocol.request.LoginRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.LoginResponsePacket;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.UUID;

/**
 * 登录请求处理器
 *
 * @author roin.zhang
 * @date 2019/10/23
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(LoginRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LOGGER.info(new Date() + ": 客户端开始登录");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUsername());
        if (vaild(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
            LOGGER.info("[" + loginRequestPacket.getUsername() + "]登录成功");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            LOGGER.info(new Date() + ": 登录失败");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean vaild(LoginRequestPacket loginRequestPacket) {
        return "123456".equals(loginRequestPacket.getPassword());
    }

    /**
     * @return 随机用户Id
     */
    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    /**
     * 用户下线后，自动删除映射关系
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
