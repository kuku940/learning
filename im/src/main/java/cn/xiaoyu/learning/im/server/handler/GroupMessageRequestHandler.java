package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.protocol.request.GroupMessageRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.GroupMessageResponsePacket;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/29
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(GroupMessageRequestHandler.class);

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        // 拿到groupId构造群聊消息的响应
        String groupId = msg.getToGroupId();
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(msg.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

        // 拿到群对应的channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
