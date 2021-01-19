package cn.xiaoyu.learning.spring.aspect;

import cn.xiaoyu.learning.spring.response.Response;
import cn.xiaoyu.learning.spring.response.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理类
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {
    /**
     * Exception异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Response handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseHelper.buildFail(e.getMessage());
    }
}

