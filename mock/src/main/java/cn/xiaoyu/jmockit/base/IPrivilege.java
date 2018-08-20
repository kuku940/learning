package cn.xiaoyu.jmockit.base;

/**
 * @author Roin zhang
 * @date 2018/7/25
 */

public interface IPrivilege {
    /**
     * 判断用户有没有权限
     *
     * @param userId 用户Id
     * @return 是否权限
     */
    boolean isAllow(long userId);
}