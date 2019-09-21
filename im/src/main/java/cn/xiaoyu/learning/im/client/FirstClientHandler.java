package cn.xiaoyu.learning.im.client;

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
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    private static final Log LOGGER = LogFactory.getLog(FirstClientHandler.class);

    /**
     * 客户端连接建立成功后被调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info(new Date() + ": 客户端写数据");
        // 获取数据
        ByteBuf buffer = getByteBuffer(ctx);
        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    /**
     * 读取数据逻辑
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        LOGGER.info(new Date() + ": 客户端读取到数据 -> " + byteBuf.toString(CharsetUtil.UTF_8));
    }

    private ByteBuf getByteBuffer(ChannelHandlerContext ctx) {
        // 获取二进制抽象 ByteBuf 内存管理器
        ByteBuf buffer = ctx.alloc().buffer();

        // 准备数据，指定字符串的字符集为UTF-8
        byte[] bytes = "Hello Netty".getBytes(CharsetUtil.UTF_8);

        // 填充数据到 ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }
}
