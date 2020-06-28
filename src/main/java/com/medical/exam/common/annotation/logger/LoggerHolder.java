package com.medical.exam.common.annotation.logger;

import com.medical.exam.common.constant.SystemConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
public class LoggerHolder {
    private static final ConcurrentHashMap<Class, Logger> CLASS_LOGGER_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>(SystemConstant.INIT_SIZE * 2);
    private static final ThreadLocal<Logger> LOGGER_THREAD_LOCAL = new ThreadLocal<>();

    public static Logger getLogger() {
        Logger logger = LOGGER_THREAD_LOCAL.get();
        if (null == logger) {
            return setDefaultLogger();
        }
        return logger;
    }

    /**
     * set default logger
     * @return
     */
    private static Logger setDefaultLogger() {
        Logger logger = CLASS_LOGGER_CONCURRENT_HASH_MAP.computeIfAbsent(Logger.class, v -> LoggerFactory.getLogger("default logger"));
        LOGGER_THREAD_LOCAL.set(logger);
        return logger;
    }

    public static void setLogger(Class clasz) {
        Logger logger = CLASS_LOGGER_CONCURRENT_HASH_MAP.computeIfAbsent(clasz, v -> LoggerFactory.getLogger(clasz));
        LOGGER_THREAD_LOCAL.set(logger);
    }

    public static void clearLogger() {
        LOGGER_THREAD_LOCAL.remove();
    }
}
