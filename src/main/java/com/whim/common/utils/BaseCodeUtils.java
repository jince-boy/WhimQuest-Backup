package com.whim.common.utils;

import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/5 10:35
 * @description: Base编码工具类
 */
public class BaseCodeUtils {
    public final static Charset DEFAULT_CHARSET = CharsetUtil.UTF_8;
    public final static Base64.Decoder decoder;
    public final static Base64.Encoder encoder;
    private static final byte[] DECODE_TABLE = {
            // 0 1 2 3 4 5 6 7 8 9 A B C D E F
            - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, // 00-0f
            - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, // 10-1f
            - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, - 1, 62, - 1, 62, - 1, 63, // 20-2f + - /
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, - 1, - 1, - 1, - 2, - 1, - 1, // 30-3f 0-9，-2的位置是'='
            - 1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, // 40-4f A-O
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, - 1, - 1, - 1, - 1, 63, // 50-5f P-Z _
            - 1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, // 60-6f a-o
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 // 70-7a p-z
    };

    static {
        decoder = Base64.getDecoder();
        encoder = Base64.getEncoder();
    }

    /**
     * byte[] Base64编码
     *
     * @param bytes byte[]
     * @return 编码后的byte[]
     */
    public static byte[] encodeBase64(byte[] bytes) {
        return encoder.encode(bytes);
    }

    /**
     * 字符串 Base64编码
     *
     * @param source 字符串
     * @return 编码后的字符串
     */
    public static String encodeBase64(String source) {
        byte[] bytes = encodeBase64(source.getBytes());
        return new String(bytes, DEFAULT_CHARSET);
    }

    /**
     * byte[] Base64编码转字符串
     *
     * @param bytes byte[] Base64编码
     * @return 转换后的字符串
     */
    public static String encodeBase64ToString(byte[] bytes) {
        return encoder.encodeToString(bytes);
    }

    /**
     * base64字符串转byte[]
     *
     * @param baseStr base64字符串
     * @return byte[]
     */
    public static byte[] encodeBase64ToByte(String baseStr) {
        return encodeBase64(baseStr.getBytes());
    }

    /**
     * Base64解码
     *
     * @param bytes byte
     * @return 解码后的byte
     */
    public static byte[] decodeBase64(byte[] bytes) {
        return decoder.decode(bytes);
    }

    /**
     * Base64字符串解码
     *
     * @param string Base64字符串
     * @return 解码后的字符串
     */
    public static String decodeBase64(String string) {
        return new String(decodeBase64(string.getBytes()), DEFAULT_CHARSET);
    }

    /**
     * Base64字符串 解码为 byte[]
     *
     * @param byteStr Base64字符串
     * @return 解码后的byte[]
     */
    public static byte[] decodeBase64ToByte(String byteStr) {
        return decoder.decode(byteStr.getBytes());
    }

    /**
     * Base64Byte解码为字符串
     *
     * @param bytes Base64byte
     * @return 解码后的字符串
     */
    public static String decodeBase64ToString(byte[] bytes) {
        return new String(decoder.decode(bytes), DEFAULT_CHARSET);
    }

    /**
     * 判断byte[]是不是Base64
     *
     * @param base64Bytes byte[]
     * @return true, false
     */
    public static Boolean isBase64(byte[] base64Bytes) {
        if (base64Bytes == null || base64Bytes.length < 3) {
            return false;
        }
        boolean hasPadding = false;
        for (byte base64Byte : base64Bytes) {
            if (hasPadding) {
                if ('=' != base64Byte) {
                    return false;
                }
            } else if ('=' == base64Byte) {
                hasPadding = true;
            } else if ((isBase64(base64Byte) || isWhiteSpace(base64Byte))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符是不是Base64字符
     *
     * @param octet 字符
     * @return true, false
     */
    public static boolean isBase64(byte octet) {
        return octet == '=' || (octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != - 1);
    }

    /**
     * 是否是特殊字符
     *
     * @param byteToCheck 字符
     * @return true, false
     */
    private static boolean isWhiteSpace(byte byteToCheck) {
        return switch (byteToCheck) {
            case ' ', '\n', '\r', '\t' -> true;
            default -> false;
        };
    }
}
