package com.whim.common.core.constant;

/**
 * @author Jince
 * @since: 2023.12.03 下午 10:47
 * @description: Redis key的常量
 */
public class RedisKeyConstants {
    /**
     * 注册邮箱验证码
     */
    public final static String REGISTER_VERIFY_CODE_KEY = "emailVerifyCode:register:";
    /**
     * 图片验证码
     */
    public final static String VERIFY_CODE_KEY = "verifyCode:{}:{}";
    /**
     * Token黑名单
     */
    public final static String TOKEN_BLOCK_KEY = "tokenBlockKey:{}";
    /**
     * 登录用户信息
     */
    public final static String LOGIN_USER_INFO = "loginUserInfo:{}:{}";
}
