package cn.xiaoyu.learning.im.server.handler;

import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 处理身份认证相关逻辑
 *
 * @author roin.zhang
 * @date 2019/10/23
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    private static final Log LOGGER = LogFactory.getLog(AuthHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            LOGGER.info("AuthHandler认证失败");
            // 未登录 将连接关闭
            ctx.channel().close();
        } else {
            LOGGER.info("AuthHandler认证成功");
            // 登录成功，使用pipeline热插拔机制移除校验逻辑，只做一次校验，登录成功后则不需要此登录认证
            // 避免资源和性能的浪费
            ctx.pipeline().remove(this);
            // 登录 继续向下执行逻辑
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())) {
            LOGGER.info("当前连接登录验证完毕，无需再次验证，AuthHandler被移除");
        } else {
            LOGGER.info("无登录验证，强制关闭连接！");
        }
    }
}
