package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(QuitGroupConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        LOGGER.info("输入要退出群聊的GroupId:");
        String groupId = scanner.nextLine();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
