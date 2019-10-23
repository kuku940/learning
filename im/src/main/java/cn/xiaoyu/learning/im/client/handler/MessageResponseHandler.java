package cn.xiaoyu.learning.im.client.handler;

import cn.xiaoyu.learning.im.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * @author roin.zhang
 * @date 2019/10/23
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    private static final Log LOGGER = LogFactory.getLog(MessageResponseHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        LOGGER.info(new Date() + ": 收到服务端的消息：" + messageResponsePacket.getMessage());
    }
}
