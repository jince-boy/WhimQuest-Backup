package com.whim.common.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whim.common.core.constant.HttpStatusConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jince
 * @since: 2023.11.30 下午 01:36
 * @description: Result工具类
 */
@Data
@Schema(name = "响应对象")
public class Result<T> implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "信息")
    private String message;
    @Schema(description = "数据")
    private T data;
    @Schema(description = "状态")
    private Boolean status;

    public Result(T data) {
        this.data = data;
    }

    public Result<T> setResult(Integer code, Boolean status, String message) {
        this.setCode(code);
        this.setStatus(status);
        this.setMessage(message);
        return this;
    }

    /**
     * 成功
     *
     * @param <T> T
     * @return Result
     */
    public static <T> Result<T> success() {
        return new Result<T>(null).setResult(HttpStatusConstants.SUCCESS, true, "成功");
    }

    /**
     * 成功
     *
     * @param message message
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> success(String message) {
        return new Result<T>(null).setResult(HttpStatusConstants.SUCCESS, true, message);
    }

    /**
     * 成功
     *
     * @param message message
     * @param data    data
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(data).setResult(HttpStatusConstants.SUCCESS, true, message);
    }

    /**
     * 成功
     *
     * @param code    code
     * @param message message
     * @param data    data
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> success(Integer code, String message, T data) {
        return new Result<>(data).setResult(code, true, message);
    }

    /**
     * 失败
     *
     * @param <T> T
     * @return Result
     */
    public static <T> Result<T> error() {
        return new Result<T>(null).setResult(HttpStatusConstants.ERROR, false, "失败");
    }

    /**
     * 失败
     *
     * @param message message
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> error(String message) {
        return new Result<T>(null).setResult(HttpStatusConstants.ERROR, false, message);
    }

    /**
     * 失败
     *
     * @param message message
     * @param data    T
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> error(String message, T data) {
        return new Result<>(data).setResult(HttpStatusConstants.ERROR, false, message);
    }

    /**
     * 失败
     *
     * @param code    Number
     * @param message message
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<T>(null).setResult(code, false, message);
    }

    /**
     * 失败
     *
     * @param code    Number
     * @param message message
     * @param data    T
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> error(Integer code, String message, T data) {
        return new Result<>(data).setResult(code, false, message);
    }

    /**
     * 参数错误
     *
     * @param <T> T
     * @return Result
     */
    public static <T> Result<T> paramErr() {
        return new Result<T>(null).setResult(HttpStatusConstants.BAD_REQUEST, false, "参数错误");
    }

    /**
     * 未认证
     *
     * @param message message
     * @param <T>     T
     * @return Result
     */
    public static <T> Result<T> unauthorized(String message) {
        return new Result<T>(null).setResult(HttpStatusConstants.UNAUTHORIZED, false, message);
    }

    /**
     * 分页
     *
     * @param page page
     * @param <T>  T
     * @return Result
     */
    public static <T> Result<RPage<T>> page(Page<T> page) {
        RPage<T> rPage = new RPage<>(page.getCurrent(), page.getRecords(), page.getPages(), page.getSize(), page.getTotal());
        return new Result<>(rPage).setResult(HttpStatusConstants.SUCCESS, true, "获取成功");
    }

}
