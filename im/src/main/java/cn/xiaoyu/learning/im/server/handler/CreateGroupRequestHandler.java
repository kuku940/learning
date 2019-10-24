package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.protocol.request.CreateGroupRequestPacket;
import cn.xiaoyu.learning.im.protocol.response.CreateGroupResponsePacket;
import cn.xiaoyu.learning.im.util.IdGenerateUtil;
import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    private static final Log LOGGER = LogFactory.getLog(CreateGroupRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIds = createGroupRequestPacket.getUserIds();

        List<String> userNames = new ArrayList<>();

        // 创建一个channel分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 筛选出待加入群聊的用户的channel和userName
        for (String userId : userIds) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNames.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        // 群聊创建结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IdGenerateUtil.randomId());
        createGroupResponsePacket.setUserNames(userNames);

        // 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        LOGGER.info("群创建成功，id为【" + createGroupResponsePacket.getGroupId() + "】，群成员为" + createGroupResponsePacket.getUserNames());
    }
}
