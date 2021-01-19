package cn.xiaoyu.learning.spring.aspect;

import cn.xiaoyu.learning.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class WebLogAspect {
    /**
     * 进入方法时间戳
     */
    private Long startTime;
    /**
     * 方法结束时间戳(计时)
     */
    private Long endTime;

    public WebLogAspect() {
    }

    /**
     * 定义请求日志切入点，其切入点表达式有多种匹配方式,这里是指定路径
     */
    @Pointcut("execution(public * cn.xiaoyu.learning.api.*.*(..))")
    public void webLogPointcut() {
    }


    @Around("webLogPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取方法参数值数组
        Object[] args = point.getArgs();

        //打印请求的内容
        startTime = System.currentTimeMillis();
        Object result = point.proceed();
        endTime = System.currentTimeMillis();
        long cost = endTime - startTime;

        String uri = request.getRequestURI();

        StringBuilder builder = new StringBuilder();
        builder.append("请求地址：").append(uri).append("\r\n");
        builder.append("请求参数：").append(Arrays.toString(args)).append("\r\n");
        builder.append("请求返回: ").append(JsonUtils.writeObject2JSON(result)).append("\r\n");
        builder.append("请求总耗时：").append(cost).append("\r\n");

        log.info(builder.toString());
        return result;
    }
}
