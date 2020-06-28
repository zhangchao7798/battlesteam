package com.medical.exam.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/06/19
 */
public class NamedThreadFactory implements ThreadFactory {
    private final String prefix;
    private final boolean daemon;
    private final AtomicInteger sequence = new AtomicInteger(1);

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        this.prefix = prefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        int threadSequence = sequence.getAndIncrement();
        Thread thread = new Thread(r,this.prefix + "-" + threadSequence);
        thread.setDaemon(this.daemon);
        return thread;
    }
}
