package com.whim.common.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.whim.common.core.constant.HttpStatusConstants;
import com.whim.common.exception.CheckVerifyCodeException;
import com.whim.common.exception.ExcelException;
import com.whim.common.exception.JwtException;
import com.whim.common.exception.ParamException;
import com.whim.common.exception.ServiceException;
import com.whim.common.exception.UserNotFoundException;
import com.whim.common.exception.UserPasswordNotMatchException;
import com.whim.common.web.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jince
 * @since: 2023.11.30 上午 02:02
 * @description: 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * HTTP 消息不可读异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.info(exception.getMessage());
        return Result.error("参数类型不匹配,请检查参数类型");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleServiceExceptionHandler(ServiceException exception) {
        log.error(exception.getMessage());
        return Result.error(exception.getMessage());
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(ParamException.class)
    public Result<String> handleParamException(ParamException exception) {
        log.error(exception.getMessage());
        return Result.error(exception.getMessage());
    }

    /**
     * 请求方式错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Integer> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持'{}'请求", e.getMethod());
        return Result.error(HttpStatusConstants.BAD_METHOD, "请求方式错误" + e.getMessage());
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException exception) {
        log.error(exception.getMessage(), exception);
        List<String> collect = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return Result.error(StringUtils.join(collect, ";"));
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        List<String> collect = exception.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return Result.error(StringUtils.join(collect, ";"));
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);
        List<String> collect = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return Result.error(StringUtils.join(collect, ";"));
    }

    /**
     * jwt异常
     */
    @ExceptionHandler(JwtException.class)
    public Result<String> handleJwtException(JwtException jwtException) {
        log.error(jwtException.getMessage(), jwtException);
        return Result.error(HttpStatusConstants.UNAUTHORIZED, jwtException.getMessage());
    }

    /**
     * Excel异常
     */
    @ExceptionHandler(ExcelException.class)
    public Result<String> handleExcelException(ExcelException exception) {
        log.error(exception.getMessage(), exception);
        return Result.error(HttpStatusConstants.ERROR, exception.getMessage());
    }

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<String> handleNotLoginException(NotLoginException exception) {
        log.error(exception.getMessage());
        return Result.error(HttpStatusConstants.UNAUTHORIZED, "用户未认证");
    }

    /**
     * 用户没有权限
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result<String> handleNotPermissionException(NotPermissionException exception) {
        log.error(exception.getMessage());
        return Result.error(HttpStatusConstants.FORBIDDEN, "用户没有权限");
    }

    /**
     * 用户不存在异常
     */
    @ExceptionHandler(UserNotFoundException.class)
    public Result<String> handleUserNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage());
        return Result.error(HttpStatusConstants.ERROR, "你登录的账户不存在");
    }

    /**
     * 用户名或密码错误异常
     */
    @ExceptionHandler(UserPasswordNotMatchException.class)
    public Result<String> handleUserPasswordNotMatchException(UserPasswordNotMatchException exception) {
        log.error(exception.getMessage());
        return Result.error(HttpStatusConstants.ERROR, "用户名或密码错误");
    }

    /**
     * 验证码错误
     */
    @ExceptionHandler(CheckVerifyCodeException.class)
    public Result<String> handleCheckVerifyCodeException(CheckVerifyCodeException exception) {
        log.error(exception.getMessage());
        return Result.error(HttpStatusConstants.ERROR, "验证码错误");
    }
}
