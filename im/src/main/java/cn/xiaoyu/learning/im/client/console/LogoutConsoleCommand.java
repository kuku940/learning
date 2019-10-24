package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(LogoutConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
