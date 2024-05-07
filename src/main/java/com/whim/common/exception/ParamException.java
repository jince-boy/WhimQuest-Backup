package com.whim.common.exception;

/**
 * @author Jince
 * @since: 2023.11.30 上午 02:03
 * @description: 参数异常类
 */
public final class ParamException extends RuntimeException {
    public ParamException(String message) {
        super(message);
    }
}
