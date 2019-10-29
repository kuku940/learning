package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.common.Session;
import cn.xiaoyu.learning.im.protocol.Packet;
import cn.xiaoyu.learning.im.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author roin.zhang
 * @date 2019/10/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
