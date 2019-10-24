package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.protocol.Packet;
import cn.xiaoyu.learning.im.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupResponsePacket extends Packet {
    private boolean success;
    private String groupId;
    private List<String> userNames;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
