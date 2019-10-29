package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.common.Session;
import cn.xiaoyu.learning.im.protocol.request.MessageRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.MessageResponsePacket;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(MessageRequestHandler.class);

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        LOGGER.info(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());

        // 拿到消息发送方的会话消息
        Session session = SessionUtil.getSession(ctx.channel());


        // 通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 拿到消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            LOGGER.error("[" + messageRequestPacket.getToUserId() + "]不在线，发送失败！");
        }
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
