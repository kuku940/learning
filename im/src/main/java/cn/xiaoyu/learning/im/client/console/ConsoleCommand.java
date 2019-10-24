package cn.xiaoyu.learning.im.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台指令集
 *
 * @author roin.zhang
 * @date 2019/10/24
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
