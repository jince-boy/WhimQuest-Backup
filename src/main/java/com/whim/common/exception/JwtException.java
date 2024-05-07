package com.whim.common.exception;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/11 9:51
 * @description: Jwt异常类
 */
public final class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
