package com.whim.common.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.whim.common.core.constant.PathConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jince
 * @since: 2024.01.16 下午 08:47
 * @description: SaToken路由拦截器
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        PathConstants.LOGIN_PATH,
                        PathConstants.VERIFY_CODE_PATH,
                        PathConstants.KNIFE4J_HTML_PATH,
                        PathConstants.KNIFE4J_RESOURCE_PATH,
                        PathConstants.KNIFE4J_DOC_PATH
                );
    }
}
