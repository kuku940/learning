package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.common.Session;
import cn.xiaoyu.learning.im.protocol.request.QuitGroupRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.QuitGroupResponsePacket;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(QuitGroupRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        LOGGER.info(session.getUserId() + "退出群聊：" + msg.getGroupId());

        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

        responsePacket.setGroupId(msg.getGroupId());
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
