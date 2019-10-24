package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cn.xiaoyu.learning.im.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author roin.zhang
 * @date 2019/10/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponsePacket extends Packet {
    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
