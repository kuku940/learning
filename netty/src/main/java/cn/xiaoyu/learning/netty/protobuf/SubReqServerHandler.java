package cn.xiaoyu.learning.netty.protobuf;

import cn.xiaoyu.ssm.protobuf.SubscribeReqProto;
import cn.xiaoyu.ssm.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by roin_zhang on 2016/10/13.
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        System.out.println("Receive client : [" + req.toString() + "]");
        ctx.writeAndFlush(resp(req.getSubReqID()));
    }

    // 构建一个对象
    private SubscribeRespProto.SubscribeResp resp(int subReqID) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("netty resp");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close(); //发生异常，关闭链路
    }
}
