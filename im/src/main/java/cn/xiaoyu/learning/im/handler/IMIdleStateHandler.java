package cn.xiaoyu.learning.im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author roin.zhang
 * @date 2019/10/29
 */
public class IMIdleStateHandler extends IdleStateHandler {
    private static final Log LOGGER = LogFactory.getLog(IMIdleStateHandler.class);
    private static final int READER_IDLE_TIME = 120;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        LOGGER.info(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
