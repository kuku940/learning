package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(SendToUserConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LOGGER.info("发送消息给某个用户：");

        String toUserId = scanner.nextLine();
        String msg = scanner.nextLine();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, msg));
    }
}
