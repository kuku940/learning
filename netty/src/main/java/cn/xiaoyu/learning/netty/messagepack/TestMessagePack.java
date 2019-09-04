package cn.xiaoyu.learning.netty.messagepack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roin_zhang on 2016/10/10.
 */
public class TestMessagePack {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("msgpack");
        list.add("java");
        list.add("api");

        MessagePack msgPack = new MessagePack();
        byte[] row = msgPack.write(list);
        List<String> l = msgPack.read(row, Templates.tList(Templates.TString));
        System.out.println(l.get(0));
        System.out.println(l.get(1));
        System.out.println(l.get(2));
    }
}
