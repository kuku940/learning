package cn.xiaoyu.learning.im.protocol.request;

import cn.xiaoyu.learning.im.protocol.Packet;
import lombok.Data;

import java.util.List;

import static cn.xiaoyu.learning.im.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIds;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
