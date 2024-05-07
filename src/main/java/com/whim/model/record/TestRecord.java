package com.whim.model.record;

import java.util.Map;

/**
 * @author Jince
 * @since: 2023.12.15 下午 06:44
 * @description: TestClient记录类
 */
public record TestRecord(Integer code, Boolean status, String message, Map<String,Object> data) {

}
