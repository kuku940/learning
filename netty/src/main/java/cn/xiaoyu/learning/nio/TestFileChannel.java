package cn.xiaoyu.learning.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/9/30.
 * 测试nio中的文件channel
 */
public class TestFileChannel {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("D:/test/1.txt", "rw");
        FileChannel fileChannel = accessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int byteRead = fileChannel.read(buffer);
        while (byteRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            fileChannel.read(buffer);
        }
        accessFile.close();
        /**
         * 这儿如果使用System.exit(0);
         * maven编译会出现如下的错误：
         * The forked VM terminated without saying properly goodbye. VM crash or System.exit called
         */
//        System.exit(0);
    }
}
