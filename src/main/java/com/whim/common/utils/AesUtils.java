package com.whim.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Jince
 * @since: 2023.12.15 下午 08:55
 * @description: Aes加密解密工具类
 */
@Slf4j
public class AesUtils {
    /**
     * AES加密
     */
    public static String encryptAES(String content, String AES_KEY, String AES_IV) {
        try {
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            byte[] enCodeFormat = AES_KEY.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            byte[] initParam = AES_IV.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(byteContent);
            // 同样对加密后数据进行 base64 编码
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encryptedBytes);
        } catch (Exception e) {
            log.error("方法[encryptAES]异常", e);
        }
        return null;
    }

    /**
     * AES解密
     */
    public static String decryptAES(String content, String AES_KEY, String AES_IV) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encryptedBytes = decoder.decode(content);
            byte[] enCodeFormat = AES_KEY.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
            byte[] initParam = AES_IV.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] result = cipher.doFinal(encryptedBytes);
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("方法[decryptAES]异常", e);
        }
        return null;
    }
}
