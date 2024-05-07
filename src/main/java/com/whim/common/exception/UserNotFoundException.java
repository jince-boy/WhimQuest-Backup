package com.whim.common.exception;

/**
 * @author: Administrator-CCENOTE
 * @since: 2024-01-10 11:13
 * @description: 用户不存在异常
 */
public final class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
