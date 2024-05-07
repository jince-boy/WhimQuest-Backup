package com.whim.common.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Jince
 * @since: 2024.01.15 下午 03:08
 * @description: BCrypt密码加密工具类
 */
public class BCryptPasswordEncoderUtils {
    private static final int DEFAULT_COST = 10;

    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(DEFAULT_COST));
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
