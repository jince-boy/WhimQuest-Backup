package com.whim.common.exception;

/**
 * @author Jince
 * @since: 2023.12.03 下午 10:59
 * @description: 业务异常类
 */
public final class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
