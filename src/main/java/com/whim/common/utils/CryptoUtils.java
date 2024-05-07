package com.whim.common.utils;

import com.whim.common.exception.ServiceException;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/5 13:01
 * @description: 验证码工具类
 */
public class CryptoUtils {
    // 字体大小
    private final Integer fontSize;
    // 字间距
    private final Integer wordSpacing;
    // 文字长度
    private final Integer number;
    // 宽
    private final Integer width;
    // 高
    private final Integer height;

    public CryptoUtils() {
        this.fontSize = 40;
        this.number = 4;
        this.wordSpacing = 5;
        this.width = this.fontSize * this.number + this.wordSpacing * (number + 1);
        this.height = fontSize + wordSpacing * 2;
    }

    public CryptoUtils(Integer fontSize, Integer number) {
        this.fontSize = fontSize;
        this.number = number;
        this.wordSpacing = 5;
        this.width = this.fontSize * this.number + this.wordSpacing * (number + 1);
        this.height = fontSize + wordSpacing * 2;
    }

    public static CryptoUtils builder() {
        return new CryptoUtils();
    }

    public static CryptoUtils builder(Integer fontSize, Integer number) {
        return new CryptoUtils(fontSize, number);
    }
    // todo 图片验证码拦截器

    /**
     * 获取base64验证码
     *
     * @return VerifyCodeBase64
     */
    public VerifyCodeBase64 getVerifyCodeBase64() {
        // 生成随机文字
        String text = RandomStringUtils.generateRandomString(true, true, true, number).toLowerCase();
        byte[] verifyCode = createImage(text);
        String base64 = BaseCodeUtils.encodeBase64ToString(verifyCode);
        return new VerifyCodeBase64(text, base64);
    }

    /**
     * 获取byte验证码
     *
     * @return VerifyCodeByte
     */
    public VerifyCodeByte getVerifyCodeByte() {
        // 生成随机文字
        String text = RandomStringUtils.generateRandomString(true, true, true, number).toLowerCase();
        byte[] verifyCode = createImage(text);
        return new VerifyCodeByte(text, verifyCode);
    }

    /**
     * 生成验证码
     *
     * @return byte[]
     */
    private byte[] createImage(String text) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        // 背景颜色
        graphics.setBackground(randomColor(100));
        // 清除矩形
        graphics.clearRect(0, 0, width, height);
        // 文字
        Font arial = new Font("Arial", Font.BOLD, fontSize);
        graphics.setFont(arial);
        graphics.setColor(randomColor(256));
        // 文字坐标
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int x = wordSpacing; // 计算起始位置使文本水平居中对齐
        int y = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent(); // 计算垂直居中对齐
        // 绘制每一个字符的坐标
        char[] characters = text.toCharArray();
        Random random = new Random();
        for (char character : characters) {
            AffineTransform transform = new AffineTransform();
            // 随机旋转角度
            // 最大旋转角度
            int maxRotation = 30;
            double rotation = random.nextInt(maxRotation * 2) - maxRotation;
            transform.rotate(Math.toRadians(rotation), x, y); // 旋转角度为30度
            graphics.setTransform(transform);
            graphics.drawString(String.valueOf(character), x, y);
            x = x + fontSize + wordSpacing;
        }
        // 绘制干扰线条
        graphics.setColor(randomColor(256));
        for (int i = 0; i < 10; i++) {
            int x1 = (int) (Math.random() * width);
            int y1 = (int) (Math.random() * height);
            int x2 = (int) (Math.random() * width);
            int y2 = (int) (Math.random() * height);
            graphics.drawLine(x1, y1, x2, y2);
        }
        graphics.dispose();

        try {
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayInputStream);
            byteArrayInputStream.flush();
            byte[] byteArray = byteArrayInputStream.toByteArray();
            byteArrayInputStream.close();
            return byteArray;
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    // 生成随机颜色
    private Color randomColor(Integer bound) {
        Random random = new Random();
        int red = random.nextInt(bound);
        int green = random.nextInt(bound);
        int blue = random.nextInt(bound);
        return new Color(red, green, blue);
    }

    @Data
    public static class VerifyCodeBase64 {
        private String text;
        private String base64;

        public VerifyCodeBase64(String text, String base64) {
            this.text = text;
            this.base64 = base64;
        }

        public String getBase64() {
            return "data:image/png;base64," + base64;
        }
    }

    @Data
    public static class VerifyCodeByte {
        private String text;
        private byte[] bytes;

        public VerifyCodeByte(String text, byte[] bytes) {
            this.text = text;
            this.bytes = bytes;
        }
    }

}
