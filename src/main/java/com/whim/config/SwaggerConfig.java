package com.whim.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Administrator-CCENOTE
 * @since: 2024-01-12 11:43
 * @description: Knife4j Swagger 配置类
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI OpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title("WhimQuest")
                        .version("1.0")
                        .description("WhimQuest开发文档")
                        .termsOfService("无")
                        .contact(new Contact().name("靳策").email("jince_hm@163.com").url("www.whimquest.com"))
        );
    }
}
