package com.whim.common.utils;

import java.util.Random;

/**
 * @author Jince
 * @since: 2023.12.03 下午 10:33
 * @description: 随机字符串工具类
 */
public class RandomStringUtils {
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";

    /**
     * 生成指定的随机字符串
     *
     * @param includeLowercase 是否要小写
     * @param includeUppercase 是否要大写
     * @param includeNumbers   是否要数字
     * @param length           生成的长度
     * @return 随机字符串
     */
    public static String generateRandomString(boolean includeLowercase, boolean includeUppercase, boolean includeNumbers, int length) {
        String characters = "";
        Random random = new Random();
        if (includeLowercase) {
            characters += LOWERCASE_LETTERS;
        }
        if (includeUppercase) {
            characters += UPPERCASE_LETTERS;
        }
        if (includeNumbers) {
            characters += NUMBERS;
        }
        if (characters.isEmpty()) {
            // 默认包含大写英文和数字
            characters = UPPERCASE_LETTERS + NUMBERS;
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String generateRandomString() {
        String characters;
        Random random = new Random();
        // 默认包含大写英文和数字
        characters = UPPERCASE_LETTERS + NUMBERS;
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }
}
