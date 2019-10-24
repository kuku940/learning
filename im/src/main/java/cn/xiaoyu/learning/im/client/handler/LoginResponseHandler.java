package cn.xiaoyu.learning.im.client.handler;

import cn.xiaoyu.learning.im.common.Session;
import cn.xiaoyu.learning.im.protocol.response.LoginResponsePacket;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/23
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    private static final Log LOGGER = LogFactory.getLog(LoginResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            LOGGER.info("[" + userName + "]登录成功, userId = " + userId);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            LOGGER.info("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("客户端连接被关闭");
    }
}
