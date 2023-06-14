package com.ly.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月14日 14:53:00
 */
public class PasswordUtil {

    private static final char[] HEX = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    /**
     *自定义简单生成盐，是一个随机生成的长度为16的字符串，每一个字符是随机的十六进制字符
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(HEX[random.nextInt(HEX.length)]);
        }
        return sb.toString();
    }

    /**
     * 密码 加密
     * @param password 密码
     */
    public static String encrypt(String password) {
        return byte2HexStr(sha256(password + PasswordUtil.salt()));
    }

    /**
     * 密码 加密
     * @param password 密码
     */
    public static String encrypt(String password, String salt) {
        return byte2HexStr(sha256(password + salt));
    }

    /**
     * 密码比较
     *
     * @param password   密码
     * @param salt       干扰码
     * @param dbPassword 数据库密码
     * @return
     */
    public static boolean equals(String password, String salt, String dbPassword) {
        return dbPassword.equals(encrypt(password, salt));
    }

    /**
     * sha256
     */
    public static byte[] sha256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * @return: 十六进制字符串
     * @params: [bytes]
     * @Descrption: 将字节数组转换成十六进制字符串
     */
    private static String byte2HexStr(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byte0 : bytes) {
            result.append(HEX[byte0 >>> 4 & 0xf]);
            result.append(HEX[byte0 & 0xf]);
        }
        return result.toString();
    }

}
