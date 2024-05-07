package com.whim.config;

import com.whim.client.TestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


/**
 * @author Jince
 * @since: 2023.12.13 下午 10:49
 * @description: WebClient配置类
 */
@Configuration
public class WebClientConfig {
    @Bean
    public TestClient getTestClient(WebClient.Builder webClientBuilder) {
        WebClient webClient = webClientBuilder.build();
        // 创建服务接口的代理对象，基于WebClient
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return factory.createClient(TestClient.class);
    }
}
