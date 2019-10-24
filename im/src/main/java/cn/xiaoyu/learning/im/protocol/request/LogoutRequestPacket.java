package cn.xiaoyu.learning.im.protocol.request;

import cn.xiaoyu.learning.im.protocol.Packet;
import lombok.Data;

import static cn.xiaoyu.learning.im.protocol.command.Command.LOGOUT_REQUEST;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
