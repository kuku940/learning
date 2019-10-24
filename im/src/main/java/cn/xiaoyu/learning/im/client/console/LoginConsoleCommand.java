package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class LoginConsoleCommand implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(LoginConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        LOGGER.info("输入用户名登录：");
        loginRequestPacket.setUsername(scanner.nextLine());
        loginRequestPacket.setPassword("123456");

        // 发送数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
