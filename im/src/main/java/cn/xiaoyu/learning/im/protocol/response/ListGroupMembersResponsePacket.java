package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.common.Session;
import cn.xiaoyu.learning.im.protocol.Packet;
import cn.xiaoyu.learning.im.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
