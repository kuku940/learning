package cn.xiaoyu.learning.im.protocol.request;

import cn.xiaoyu.learning.im.protocol.Packet;
import cn.xiaoyu.learning.im.protocol.command.Command;
import lombok.Data;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
