package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(ListGroupMembersConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        LOGGER.info("输入想获取的群成员列表的群组GroupId:");
        String groupId = scanner.nextLine();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
