package com.whim.common.core.constant;


import lombok.extern.slf4j.Slf4j;

/**
 * @author Jince
 * @since: 2023.12.24 上午 01:07
 * @description: Spring Security 常量
 */
@Slf4j
public class PathConstants {
    // 登录路径
    public static final String LOGIN_PATH = "/admin/auth/login";
    // 图片验证码路径
    public static final String VERIFY_CODE_PATH = "/admin/auth/verifyCode";
    // 退出登陆路径
    public static final String LOGOUT_PATH = "/admin/auth/logout";

    public static final String FAVICON_PATH = "/favicon.ico";
    // knife4j相关路径
    public static final String KNIFE4J_HTML_PATH = "/doc.html";
    public static final String KNIFE4J_RESOURCE_PATH = "/webjars/**";
    public static final String KNIFE4J_DOC_PATH = "/v3/api-docs/**";
}
