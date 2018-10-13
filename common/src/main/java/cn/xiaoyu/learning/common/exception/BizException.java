package cn.xiaoyu.learning.common.exception;

/**
 * 业务统一异常类
 *
 * @author Roin zhang
 * @date 2018/8/22
 */

public class BizException extends RuntimeException {
    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
