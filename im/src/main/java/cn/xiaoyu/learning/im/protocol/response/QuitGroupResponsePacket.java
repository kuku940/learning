package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.protocol.Packet;
import cn.xiaoyu.learning.im.protocol.command.Command;
import lombok.Data;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupId;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
