package cn.xiaoyu.learning.netty.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created by roin_zhang on 2016/10/10.
 * msgPack编码器
 */
public class MsgpackEncoder extends MessageToByteEncoder<UserInfo> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, UserInfo o, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack = new MessagePack();
        byte[] raw = msgpack.write(o);
        byteBuf.writeBytes(raw);
    }
}
