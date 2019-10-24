package cn.xiaoyu.learning.im.util;

import cn.xiaoyu.learning.im.common.Attributes;
import cn.xiaoyu.learning.im.common.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author roin.zhang
 * @date 2019/10/10
 */
public class SessionUtil {
    private static final Log LOGGER = LogFactory.getLog(SessionUtil.class);
    /**
     * userId -> channel映射关系
     */
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    /**
     * groupId -> channelGroup映射关系
     */
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session sesion = getSession(channel);
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
            LOGGER.info(sesion + "退出登录");
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

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }
}
