package cn.xiaoyu.learning.im.client.handler;

import cn.xiaoyu.learning.im.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    private static final Log LOGGER = LogFactory.getLog(CreateGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        LOGGER.info("群创建成功，id为【" + createGroupResponsePacket.getGroupId() + "】，群成员为【" + createGroupResponsePacket.getUserNames() + "】");
    }
}
