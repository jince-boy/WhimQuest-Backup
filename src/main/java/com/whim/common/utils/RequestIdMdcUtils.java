package com.whim.common.utils;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Jince
 * @since: 2023.12.12 下午 10:47
 * @description: MDC日志链条唯一标识工具类
 */
public class RequestIdMdcUtils {
    public final static String REQUEST_ID = "requestId";

    /**
     * 获取唯一标识
     */
    public static String getRequestId() {
        return UUIDUtils.generateUUID();
    }

    /**
     * 设置唯一标识
     */
    public static void setRequestId() {
        if (MDC.get(REQUEST_ID) == null) {
            MDC.put(REQUEST_ID, getRequestId());
        }
    }

    // 用于父线程向线程池中提交任务时，将自身MDC中的数据复制给子线程
    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setRequestId();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    // 用于父线程向线程池中提交任务时，将自身MDC中的数据复制给子线程
    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setRequestId();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
