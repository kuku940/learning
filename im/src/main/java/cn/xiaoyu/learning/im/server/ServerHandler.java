package cn.xiaoyu.learning.im.server;

import cn.xiaoyu.learning.im.protocol.command.Packet;
import cn.xiaoyu.learning.im.protocol.command.PacketCodeC;
import cn.xiaoyu.learning.im.protocol.request.LoginRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * @author roin.zhang
 * @date 2019/9/24
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Log LOGGER = LogFactory.getLog(ServerHandler.class);

    /**
     * 接收到客户端发来的数据后被调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info(new Date() + ": 客户端开始登录");
        ByteBuf requestByteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        // 判断是否是登陆请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginRequestPacket.setVersion(packet.getVersion());
            if (vaild(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                LOGGER.info(new Date() + ": 登录成功");
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
                LOGGER.info(new Date() + ": 登录失败");
            }

            // 登录响应
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    private boolean vaild(LoginRequestPacket loginRequestPacket) {
        return "jack".equals(loginRequestPacket.getUsername()) || "123456".equals(loginRequestPacket.getPassword());
    }
}
