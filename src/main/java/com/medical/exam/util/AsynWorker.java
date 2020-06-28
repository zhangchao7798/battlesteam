package com.medical.exam.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/06/19
 */
@Component
public class AsynWorker {
    private static final int POOL_SIZE = 5;
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(POOL_SIZE,
        POOL_SIZE
        , Integer.MAX_VALUE, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new NamedThreadFactory("asyn-worker"));

    /**
     * execute task
     *
     * @param runnable
     */
    public void executeTask(Runnable runnable) {
        EXECUTOR_SERVICE.execute(runnable);
    }

    /**
     * submit task
     * @param runnable
     * @return
     */
    public Future submitTask(Runnable runnable) {
        return EXECUTOR_SERVICE.submit(runnable);
    }

    /**
     * submit task
     * @param callable
     * @return
     */
    public Future submitTask(Callable callable) {
        return EXECUTOR_SERVICE.submit(callable);
    }
}
