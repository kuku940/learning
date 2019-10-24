package cn.xiaoyu.learning.im.protocol.request;

import cn.xiaoyu.learning.im.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cn.xiaoyu.learning.im.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author roin.zhang
 * @date 2019/10/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
