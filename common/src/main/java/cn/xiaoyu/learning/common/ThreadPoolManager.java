package cn.xiaoyu.learning.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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
    private static final int CORE_POOL_SIZE = 20;
    private static final int MAXIMUM_POOL_SIZE = 30;
    private static ExecutorService instance;

    public static ExecutorService getInstance() {
        ExecutorService localInstance = instance;
        if (localInstance == null) {
            synchronized (ThreadPoolManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new ThreadPoolExecutor(CORE_POOL_SIZE,
                            MAXIMUM_POOL_SIZE,
                            30000L,
                            TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<>(),
                            // google guava线程池命名工具
                            new ThreadFactoryBuilder().setNameFormat("learning-%d").build());
                }
            }
        }
        return localInstance;
    }
}
