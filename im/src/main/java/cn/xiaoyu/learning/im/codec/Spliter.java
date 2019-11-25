package cn.xiaoyu.learning.im.codec;

import cn.xiaoyu.learning.im.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 自定义基于长度域拆包器
 * <p>
 * 通讯协议格式：
 * 魔数0x12345678     版本号1        序列化算法       指令      数据长度        数据
 * 4字节              1字节         1字节            1字节      4字节         N字节
 * <p>
 * netty的再带拆包器：
 * 1. 固定长度的拆包器 - FixedLengthFrameDecoder
 * 2. 行拆包器 - LineBasedFrameDecoder
 * 3. 分隔符拆包器 - DelimiterBasedFrameDecoder
 * 4. 基于长度域的拆包器 - LengthFieldBasedFrameDecoder
 *
 * @author roin.zhang
 * @date 2019/10/23
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 拒绝非本协议连接的客户端
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        // 拆包操作
        return super.decode(ctx, in);
    }
}
