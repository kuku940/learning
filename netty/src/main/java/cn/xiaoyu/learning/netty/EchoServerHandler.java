package cn.xiaoyu.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/2.
 * 考虑拆包和粘包
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private int counter = 0;

    @Override
    /**
     * 表明一个ChannelHandler可以被多个Channel安全地共享
     * 每次接收消息时被调用
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //直接打印读取到的字符
        System.out.println("Server received:" + msg + ";this counter is:" + ++counter);

        //输出服务器端接受到的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = "currentTime is " + sdf.format(new Date());
        ByteBuf resp = Unpooled.copiedBuffer(currentTime + System.getProperty("line.separator"), CharsetUtil.UTF_8);
        ctx.writeAndFlush(resp);
    }

    @Override
    /**
     * 用来通知handler上一个ChannelRead()是被这批消息中的最后一个消息调用
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // flush所有待审消息到远程节点，关闭通道，操作完成
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
