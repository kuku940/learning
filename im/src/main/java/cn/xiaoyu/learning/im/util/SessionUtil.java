package cn.xiaoyu.learning.im.util;

import cn.xiaoyu.learning.im.common.Attributes;
import cn.xiaoyu.learning.im.common.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author roin.zhang
 * @date 2019/10/10
 */
public class SessionUtil {
    /**
     * userId -> channel映射关系
     */
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }


    /**
     * 是否登录
     *
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Channel getChannel(String toUserId) {
        return userIdChannelMap.get(toUserId);
    }
}
