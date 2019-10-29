package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.protocol.request.JoinGroupRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.JoinGroupResponsePacket;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(JoinGroupRequestHandler.class);

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        // 获取群对应的channelGroup,然后将当前的用户的channel添加进去
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 构造加群响应发送客户端
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
