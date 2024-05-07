package com.whim.common.core.base;

import lombok.Data;

/**
 * @author Jince
 * @since: 2023.12.13 下午 11:32
 * @description: Client Response基类
 */
@Data
public class BaseResponse {
    private Integer code;
    private String message;
    private Object data;
    private Boolean status;
}
