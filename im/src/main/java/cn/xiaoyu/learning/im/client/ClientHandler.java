package cn.xiaoyu.learning.im.client;

import cn.xiaoyu.learning.im.protocol.command.PacketCodeC;
import cn.xiaoyu.learning.im.protocol.request.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.UUID;

/**
 * @author roin.zhang
 * @date 2019/9/24
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static final Log LOGGER = LogFactory.getLog(ClientHandler.class);

    /**
     * 客户端连接建立成功后被调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info(new Date() + ": 客户端客户端开始登陆");

        // 构建登陆对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("jack");
        loginRequestPacket.setPassword("123456");

        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

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
}
