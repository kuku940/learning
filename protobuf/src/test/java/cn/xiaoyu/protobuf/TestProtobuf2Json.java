package cn.xiaoyu.protobuf;

import cn.xiaoyu.protobuf.domain.UserProto.Url;
import cn.xiaoyu.protobuf.domain.UserProto.User;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

/**
 * @author roin.zhang
 * @date 2019/10/8
 */
public class TestProtobuf2Json {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        User u = User.newBuilder().setId(1).setName("jack")
                .addUrls(Url.newBuilder().setName("baidu").setDetail("baidu.com"))
                .addUrls(Url.newBuilder().setName("google").setDetail("google.com"))
                .putContactMap("phone", "13812345678")
                .putContactMap("tel", "23456789")
                .build();

        // proto to json
        String json = JsonFormat.printer().includingDefaultValueFields().print(u);
        System.out.println(json);

        // json to proto
        User.Builder builder = User.newBuilder();
        JsonFormat.parser().merge(json, builder);
        User user = builder.build();
        System.out.println(user.getContactMapMap().get("phone"));
    }
}
