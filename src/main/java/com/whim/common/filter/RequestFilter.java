package com.whim.common.filter;

import com.whim.common.utils.IpUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Jince
 * @since: 2023.12.12 下午 10:09
 * @description: 全局请求过滤器
 */
@Slf4j
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("当前IP地址为:[ " + IpUtils.getIpAddress((HttpServletRequest) servletRequest) + " ]的设备请求了路由:{ " + request.getRequestURI() + " }");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
