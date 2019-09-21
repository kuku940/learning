package cn.xiaoyu.learning.im.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * @author Roin zhang
 * @date 2019/9/21
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    private static final Log LOGGER = LogFactory.getLog(FirstServerHandler.class);

    /**
     * 接收到客户端发来的数据后被调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        LOGGER.info(new Date() + ": 服务器读到数据 -> " + byteBuf.toString(CharsetUtil.UTF_8));
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "Hello Client".getBytes(CharsetUtil.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }
}
