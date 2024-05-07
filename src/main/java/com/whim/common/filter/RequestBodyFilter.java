package com.whim.common.filter;

import com.whim.common.filter.wrapper.BodyReaderRequestWrapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author Jince
 * @since: 2023.12.26 下午 05:26
 * @description: 请求体json过滤器, 方式过滤器获取到数据之后, controller获取不到数据
 */
public class RequestBodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request = null;
        if (servletRequest instanceof HttpServletRequest && StringUtils.startsWithIgnoreCase(servletRequest.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            request = new BodyReaderRequestWrapper((HttpServletRequest) servletRequest, servletResponse);
        }
        if (request == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(request, servletResponse);
        }
    }
}
