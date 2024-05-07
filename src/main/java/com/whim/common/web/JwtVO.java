package com.whim.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jince
 * @since: 2024.01.15 下午 03:46
 * @description: JWT实体类
 */
@Data
@AllArgsConstructor
public class JwtVO {
    private String token;
    private Long expires;
}
