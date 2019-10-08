package cn.xiaoyu.protobuf;

import cn.xiaoyu.protobuf.domain.UserProto.Url;
import cn.xiaoyu.protobuf.domain.UserProto.User;

public class TestProtobuf {
    public static void main(String[] args) {
      User u = User.newBuilder().setId(1).setName("jack")
              .addUrls(Url.newBuilder().setName("baidu").setDetail("baidu.com"))
              .addUrls(Url.newBuilder().setName("google").setDetail("google.com"))
              .putContactMap("phone", "13812345678")
              .putContactMap("tel", "23456789")
              .build();
        System.out.println(u.getContactMapMap().get("phone"));
    }
}
