package cn.xiaoyu.learning.im.codec;

import cn.xiaoyu.learning.im.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author roin.zhang
 * @date 2019/10/23
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 将二进制数据转换成Java对象 - 使用ByteToMessageDecoder，netty会自动进行内存释放
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
