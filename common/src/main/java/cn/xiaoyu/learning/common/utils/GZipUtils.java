package cn.xiaoyu.learning.common.utils;

import cn.xiaoyu.learning.common.exception.BizException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Gzip压缩解压缩工具类
 *
 * @author Roin zhang
 * @date 2018/8/22
 */

public class GZipUtils {
    public static byte[] compress(byte[] data) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            gzip.write(data);
            gzip.finish();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new BizException("Gzip压缩失败", e);
        }
    }

    public static byte[] uncompress(byte[] data) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data); GZIPInputStream gzip = new GZIPInputStream(bis);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int num = -1;
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                bos.write(buf, 0, num);
            }

            byte[] ret = bos.toByteArray();
            bos.flush();
            return ret;
        } catch (IOException e) {
            throw new BizException("Gzip解压缩失败", e);
        }
    }
}
