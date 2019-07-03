package cn.xiaoyu.protobuf;

import cn.xiaoyu.protobuf.domain.UserBean;
import cn.xiaoyu.protobuf.domain.UserProto.User;
import cn.xiaoyu.protobuf.util.ProtobufTransform;

public class TestProtobufTransform {
  public static void main(String[] args) {
    User u = User.newBuilder().setId(1).setEmail("jack@ctrip.com").setName("jack").build();
    UserBean bean = ProtobufTransform.fromProtoBuffer(u, UserBean.class);
    System.out.println(bean.getName() + " - " + bean.getEmail());

    UserBean userBean = new UserBean();
    userBean.setId(10086);
    userBean.setName("大耳贼");
    userBean.setEmail("dez@ctrip.com");

    User user = ProtobufTransform.toProtoBuffer(userBean, User.class);
    System.out.println(user.getName() + "-" + user.getEmail());
  }
}
