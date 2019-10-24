package cn.xiaoyu.learning.im.client.console;

import cn.xiaoyu.learning.im.util.SessionUtil;
import io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
public class ConsoleCommandMananger implements ConsoleCommand {
    private static final Log LOGGER = LogFactory.getLog(ConsoleCommandMananger.class);
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandMananger() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.nextLine();

        if (!SessionUtil.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            LOGGER.error("无法识别【" + command + "】指令，请重新输入！");
        }
    }

    public Map<String, ConsoleCommand> getConsoleCommandMap() {
        return consoleCommandMap;
    }
}
