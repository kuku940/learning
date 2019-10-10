package cn.xiaoyu.learning.im.protocol.command;

/**
 * @author roin.zhang
 * @date 2019/9/24
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
