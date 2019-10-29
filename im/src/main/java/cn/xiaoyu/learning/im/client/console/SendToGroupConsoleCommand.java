package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/29
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(SendToGroupConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LOGGER.info("发送消息给哪个群组：");

        String toGroupId = scanner.nextLine();
        String message = scanner.nextLine();

        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
