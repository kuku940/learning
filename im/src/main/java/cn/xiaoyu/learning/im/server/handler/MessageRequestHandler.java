package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.protocol.request.MessageRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * 消息请求处理器
 *
 * @author roin.zhang
 * @date 2019/10/23
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(MessageRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        LOGGER.info(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复：" + messageRequestPacket.getMessage());
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
