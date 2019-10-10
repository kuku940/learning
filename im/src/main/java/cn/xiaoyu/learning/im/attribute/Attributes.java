package cn.xiaoyu.learning.im.attribute;

import io.netty.util.AttributeKey;

/**
 * @author roin.zhang
 * @date 2019/10/10
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
