package cn.xiaoyu.learning.im.protocol.response;

import cn.xiaoyu.learning.im.protocol.Packet;
import cn.xiaoyu.learning.im.protocol.command.Command;
import lombok.Data;

/**
 * @author roin.zhang
 * @date 2019/9/24
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;
    private String userId;
    private String userName;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
