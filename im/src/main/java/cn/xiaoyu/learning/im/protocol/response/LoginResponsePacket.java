package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.protocol.command.Command;
import cn.xiaoyu.learning.im.protocol.Packet;
import lombok.Data;

/**
 * @author roin.zhang
 * @date 2019/9/24
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
