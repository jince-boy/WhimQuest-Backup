package com.whim.common.exception;

/**
 * @author: Administrator-CCENOTE
 * @since: 2024-01-10 11:12
 * @description: 用户密码不匹配异常
 */
public final class UserPasswordNotMatchException extends RuntimeException{
    public UserPasswordNotMatchException(String message) {
        super(message);
    }
}
