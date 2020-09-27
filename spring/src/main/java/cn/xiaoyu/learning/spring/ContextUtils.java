package cn.xiaoyu.learning.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring上下文的工具类
 *
 * @author 01399268
 * @date 2020/9/27
 */
@Component
public class ContextUtils implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 根据Bean名称获取Bean对象
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 根据Bean的类型获取对应的Bean
     */
    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    /**
     * 根据Bean名称获取指定类型的Bean对象
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return context.getBean(name, requiredType);
    }

    /**
     * 判断是否包含对应名称的Bean对象
     */
    public static boolean containsBean(String name) {
        return context.containsBean(name);
    }

    /**
     * 获取对应Bean名称的类型
     */
    public static Class<?> getType(String name) {
        return context.getType(name);
    }

    /**
     * 获取上下文对象，可进行各种Spring的上下文操作
     */
    public static ApplicationContext getContext() {
        return context;
    }
}
