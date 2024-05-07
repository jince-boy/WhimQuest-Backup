package com.whim.common.exception;

/**
 * @author Jince
 * @since: 2024.01.19 上午 10:25
 * @description: 验证码异常类
 */
public final class CheckVerifyCodeException extends RuntimeException {
    public CheckVerifyCodeException(String message) {
        super(message);
    }
}
