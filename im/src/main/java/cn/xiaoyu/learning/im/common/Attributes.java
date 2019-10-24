package cn.xiaoyu.learning.im.common;

import io.netty.util.AttributeKey;

/**
 * @author roin.zhang
 * @date 2019/10/10
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
