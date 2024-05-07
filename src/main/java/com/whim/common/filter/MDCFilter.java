package com.whim.common.filter;

import com.whim.common.utils.RequestIdMdcUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * @author Jince
 * @since: 2023.12.12 下午 10:54
 * @description: MDC日志链路过滤器
 */
@Slf4j
public class MDCFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestId = request.getHeader(RequestIdMdcUtils.REQUEST_ID);
        if (StringUtils.isBlank(requestId)) {
            requestId = RequestIdMdcUtils.getRequestId();
        }
        MDC.put(RequestIdMdcUtils.REQUEST_ID, requestId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(RequestIdMdcUtils.REQUEST_ID);
        }

    }

    @Override
    public void destroy() {
        MDC.remove(RequestIdMdcUtils.REQUEST_ID);
    }
}
