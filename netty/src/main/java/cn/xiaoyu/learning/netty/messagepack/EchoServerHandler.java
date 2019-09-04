package cn.xiaoyu.learning.netty.messagepack;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Administrator on 2016/10/2.
 * 考虑拆包和粘包
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    /**
     * 表明一个ChannelHandler可以被多个Channel安全地共享
     * 每次接收消息时被调用
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserInfo info = (UserInfo) msg;
        //直接打印读取到的字符
        System.out.println("Server received:" + info);

        //输出服务器端接受到的时间
        UserInfo user = new UserInfo("xiaoyu", 21);
        ctx.writeAndFlush(user);
    }

    @Override
    /**
     * 用来通知handler上一个ChannelRead()是被这批消息中的最后一个消息调用
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //刷新挂起的数据到远端然后关闭Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    /**
     * 在读操作异常被抛出时被调用
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常堆栈跟踪信息
        cause.printStackTrace();
        // 关闭这个channel
        ctx.close();
    }
}
