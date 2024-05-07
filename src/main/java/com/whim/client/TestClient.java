package com.whim.client;

import com.whim.model.record.TestRecord;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

/**
 * @author Jince
 * @since: 2023.12.13 下午 10:52
 * @description: 测试HTTP请求服务接口
 */
@HttpExchange("https://api.ccenote.com/api/back/admin/getVerifyCode")
public interface TestClient {
    @GetExchange
    Mono<TestRecord> getVerifyCode();
}
