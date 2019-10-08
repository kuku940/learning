package cn.xiaoyu.protobuf;

import cn.xiaoyu.protobuf.domain.UrlBean;
import cn.xiaoyu.protobuf.domain.UserBean;
import cn.xiaoyu.protobuf.domain.UserProto;
import cn.xiaoyu.protobuf.util.ProtostuffUtil;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Roin zhang
 * @date 2019/7/30
 */
public class TestProtostuff {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        UserBean userBean = new UserBean();
        userBean.setId(1);
        userBean.setName("Jack");
        userBean.setContactMap(new HashMap<>(4));
        userBean.getContactMap().put("phone", "13812345678");
        userBean.getContactMap().put("tel", "23456789");

        userBean.setUrls(new ArrayList<>());
        userBean.getUrls().add(new UrlBean("baidu", "baidu.com"));
        userBean.getUrls().add(new UrlBean("google", "google.com"));

        byte[] bytes = ProtostuffUtil.serializer(userBean);
        UserBean bean = ProtostuffUtil.deserializer(bytes, UserBean.class);
        System.out.println(bean.getContactMap().get("tel"));


        UserProto.User user = UserProto.User.parseFrom(bytes);
        System.out.println(user.getUrls(0).getDetail());
        // Map对象无法通过protobuff来序列化
        System.out.println(user.getContactMapMap().get("phone"));
    }
}
