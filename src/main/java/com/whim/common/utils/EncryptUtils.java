package com.whim.common.utils;

import java.security.SecureRandom;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/1 15:47
 * @description: 密码生成工具
 */
public class EncryptUtils {
    private final static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final static SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
