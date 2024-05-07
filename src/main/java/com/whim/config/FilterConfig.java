package com.whim.config;

import com.whim.common.filter.MDCFilter;
import com.whim.common.filter.RequestBodyFilter;
import com.whim.common.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author Jince
 * @since: 2023.12.12 下午 10:29
 * @description: 过滤器配置类
 */
@Configuration
public class FilterConfig {
    /**
     * 请求体 JSON重复使用过滤器
     */
    @Bean
    public FilterRegistrationBean<RequestBodyFilter> createRequestBodyFilterRegistrationBean(){
        FilterRegistrationBean<RequestBodyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestBodyFilter());
        registrationBean.setName("RequestBodyFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registrationBean;
    }
    /**
     * 全局请求过滤器
     */
    @Bean
    public FilterRegistrationBean<RequestFilter> createRequestFilterRegistrationBean() {
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestFilter());
        registrationBean.setName("RequestFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return registrationBean;
    }

    /**
     * MDCFilter 链路追踪过滤器
     */
    @Bean
    public FilterRegistrationBean<MDCFilter> createMDCFilterRegistrationBean() {
        FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MDCFilter());
        registrationBean.setName("MDCFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
