package com.whim.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Administrator-CCENOTE
 * @since: 2024-01-12 17:40
 * @description: 跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的域名
        registry.addMapping("/**")
                //设置允许跨域的域名
                .allowedOriginPatterns("*")
                //是否允许cookie
                .allowCredentials(true)
                //设置允许请求的方式
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                //设置允许的请求头
                .allowedHeaders("*")
                //跨域允许时间
                .maxAge(3600);
    }

}
