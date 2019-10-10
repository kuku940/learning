package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.protocol.command.Packet;
import lombok.Data;

import static cn.xiaoyu.learning.im.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author roin.zhang
 * @date 2019/10/10
 */
@Data
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
