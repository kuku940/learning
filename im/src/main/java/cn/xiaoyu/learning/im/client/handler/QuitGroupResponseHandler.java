package cn.xiaoyu.learning.im.client.handler;

import cn.xiaoyu.learning.im.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    private static final Log LOGGER = LogFactory.getLog(QuitGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) {
        if (responsePacket.isSuccess()) {
            LOGGER.info("退出群聊[" + responsePacket.getGroupId() + "]成功！");
        } else {
            LOGGER.info("退出群聊[" + responsePacket.getGroupId() + "]失败！");
        }
    }
}
