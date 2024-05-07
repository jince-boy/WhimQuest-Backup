package com.whim.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.whim.common.web.Result;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jince
 * @since: 2023.11.30 下午 06:13
 * @description: Servlet工具类
 */
public class ServletUtils {
    /**
     * 获取请求属性
     *
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        try {
            return Objects.requireNonNull(getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回请求头中的语言
     *
     * @return 语言
     */
    public static String getLanguage() {
        if (StringUtils.isNotBlank(Objects.requireNonNull(getRequest()).getHeader("Accept-Language"))) {
            return getRequest().getHeader("Accept-Language");
        }
        return "en";
    }

    /**
     * 获取String参数
     *
     * @param name 参数名
     * @return 参数值
     */
    public static String getParameter(String name) {
        return Objects.requireNonNull(getRequest()).getParameter(name);
    }

    /**
     * 获取String参数
     *
     * @param name         参数名
     * @param defaultValue 默认值
     * @return 参数值
     */
    public static String getParameter(String name, String defaultValue) {
        return ConvertUtils.toStr(Objects.requireNonNull(getRequest()).getParameter(name), defaultValue);
    }

    /**
     * 返回分页参数PageNum
     *
     * @return PageNum
     */
    public static Integer getPageNum() {
        return getParameterToInt("pageNum", 1);
    }

    /**
     * 返回分页参数PageSize
     *
     * @return PageSize
     */
    public static Integer getPageSize() {
        return getParameterToInt("pageSize", 10);
    }

    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return ConvertUtils.toInt(Objects.requireNonNull(getRequest()).getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return ConvertUtils.toInt(Objects.requireNonNull(getRequest()).getParameter(name), defaultValue);
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name) {
        return ConvertUtils.toBool(Objects.requireNonNull(getRequest()).getParameter(name));
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        return ConvertUtils.toBool(Objects.requireNonNull(getRequest()).getParameter(name), defaultValue);
    }

    /**
     * 获得所有请求参数
     *
     * @param request 请求对象{@link ServletRequest}
     * @return Map
     */
    public static Map<String, String[]> getParams(ServletRequest request) {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }

    /**
     * 获得所有请求参数
     *
     * @param request 请求对象{@link ServletRequest}
     * @return Map
     */
    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : getParams(request).entrySet()) {
            params.put(entry.getKey(), StringUtils.join(entry.getValue(), ","));
        }
        return params;
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }

    /**
     * 获取请求域名
     */
    public static String getBaseUrl() {
        return Objects.requireNonNull(getRequest()).getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort();
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value, int code) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status   http状态码
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value, int code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response    ServerHttpResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param code        响应状态码
     * @param value       响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status, Object value, int code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        Result<?> result;
        if (code == 200) {
            result = Result.success(code, value.toString(), null);
        } else {
            result = Result.error(code, value.toString(), null);
        }
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 过滤器返回json对象
     *
     * @param response HttpServletResponse
     * @param code     状态码
     * @param status   状态
     * @param message  消息
     * @param data     数据
     * @param <T>      对象
     * @throws IOException io异常
     */
    public static <T> void responseWriter(HttpServletResponse response, Integer code, Boolean status, String message, T data) throws IOException {
        Result<T> result;
        if (status) {
            result = Result.success(code, message, data);
        } else {
            result = Result.error(code, message, data);
        }
        String jsonResponse = JSONObject.toJSONString(result);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        try (PrintWriter writer = response.getWriter()) {
            writer.print(jsonResponse);
        }
    }
}
