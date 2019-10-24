package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(CreateGroupConsoleCommand.class);
    private static final String USER_ID_SPLITZER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        LOGGER.info("【拉入群聊】输入UserId列表，之间用逗号隔开：");
        String userIds = scanner.nextLine();
        createGroupRequestPacket.setUserIds(Arrays.asList(userIds.split(USER_ID_SPLITZER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
