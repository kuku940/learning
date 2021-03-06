package cn.xiaoyu.learning.nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/10/2.
 * 使用io,nio以及内存映射 读取拷贝文件的性能比较
 */
public class TestMappedByteBuffer {
    public static void main(String[] args) throws IOException {
        String srcPath = "D:/test/1.pdf";
        String descPath = "D:/test/2.pdf";

        traditionalCopy(srcPath, descPath);
        deleteFile(descPath);
        System.out.println("----------------");
        testNioCopyFile(srcPath, descPath);
        deleteFile(descPath);
        System.out.println("----------------");
        testMappedByteBuffer(srcPath, descPath);
        deleteFile(descPath);
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * @throws IOException
     */
    public static void traditionalCopy(String srcPath, String descPath) throws IOException {
        System.out.println("传统IO读取数据");
        long start = System.currentTimeMillis();
        InputStream in = new FileInputStream(srcPath);
        OutputStream out = new FileOutputStream(descPath);

        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }

        in.close();
        out.close();

        System.out.println("消耗时间：" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void testNioCopyFile(String srcPath, String descPath) throws IOException {
        System.out.println("NIO拷贝数据");
        long start = System.currentTimeMillis();
        FileInputStream in = new FileInputStream(srcPath);
        FileOutputStream out = new FileOutputStream(descPath);

        FileChannel srcChannel = in.getChannel();
        FileChannel descChannel = out.getChannel();

        descChannel.transferFrom(srcChannel, 0, srcChannel.size());

        srcChannel.close();
        descChannel.close();
        in.close();
        out.close();

        System.out.println("消耗时间：" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void testMappedByteBuffer(String srcPath, String descPath) throws IOException {
        System.out.println("内存映射拷贝数据");
        long start = System.currentTimeMillis();
        FileInputStream in = new FileInputStream(srcPath);
        FileOutputStream out = new FileOutputStream(descPath);
        try {
            FileChannel srcChannel = in.getChannel();
            FileChannel descChannel = out.getChannel();

            MappedByteBuffer mbb = srcChannel.map(FileChannel.MapMode.READ_ONLY, 0, srcChannel.size());
            descChannel.write(mbb);
            System.out.println("消耗时间：" + (System.currentTimeMillis() - start) + "ms");
            srcChannel.close();
            descChannel.close();
        } finally {
            in.close();
            out.close();
        }
    }
}
