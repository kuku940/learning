package cn.xiaoyu.learning.im.client.handler;

import cn.xiaoyu.learning.im.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    private static final Log LOGGER = LogFactory.getLog(JoinGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket msg) throws Exception {
        LOGGER.info("群聊[" + msg.getGroupId() + "]中包含" + msg.getSessionList());
    }
}
