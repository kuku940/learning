package cn.xiaoyu.learning.im.protocol.request;

import cn.xiaoyu.learning.im.protocol.command.Command;
import cn.xiaoyu.learning.im.protocol.Packet;
import lombok.Data;

/**
 * @author roin.zhang
 * @date 2019/9/24
 */
@Data
public class LoginRequestPacket extends Packet {
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
