package com.whim.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/1 16:19
 * @description: UUID工具类
 */
public class UUIDUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * 生成随机的UUID字符串（不带横线）
     *
     * @return 随机UUID字符串
     */
    public static String generateUUID() {
        UUID uuid = generateSecureUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 生成指定数量的随机UUID字符串数组（不带横线）
     *
     * @param count 数量
     * @return 随机UUID字符串数组
     */
    public static String[] generateUUIDs(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than zero");
        }
        String[] uuids = new String[count];
        for (int i = 0; i < count; i++) {
            uuids[i] = generateUUID();
        }
        return uuids;
    }

    private static UUID generateSecureUUID() {
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);
        randomBytes[6] &= 0x0F;  // 设置版本号为4
        randomBytes[6] |= 0x40;  // 设置为随机UUID
        randomBytes[8] &= 0x3F;  // 设置Variant标识
        randomBytes[8] |= (byte) 0x80;  // 设置为随机UUID
        return UUID.nameUUIDFromBytes(randomBytes);
    }
}
