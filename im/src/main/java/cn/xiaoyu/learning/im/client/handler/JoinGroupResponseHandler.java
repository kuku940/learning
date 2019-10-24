package cn.xiaoyu.learning.im.client.handler;

import cn.xiaoyu.learning.im.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    private static final Log LOGGER = LogFactory.getLog(JoinGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            LOGGER.info("加入群聊[" + joinGroupResponsePacket.getGroupId() + "]成功");
        } else {
            LOGGER.info("加入群聊[" + joinGroupResponsePacket.getGroupId() + "]失败，原因：" + joinGroupResponsePacket.getReason());
        }
    }
}
