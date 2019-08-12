package cn.xiaoyu.learning.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理器
 *
 * @author Roin zhang
 */

public class ThreadPoolManager {
    private static volatile ExecutorService instance;
    private static final int CORE_POOL_SIZE = 20;
    private static final int MAXIMUM_POOL_SIZE = 30;

    public static ExecutorService getInstance() {
        if (instance == null) {
            synchronized (ThreadPoolManager.class) {
                if (instance == null) {
                    instance = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                            30000L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>());
                }
            }
        }
        return instance;
    }
}
