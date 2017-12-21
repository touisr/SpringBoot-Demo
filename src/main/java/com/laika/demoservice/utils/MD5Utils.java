package com.laika.demoservice.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/9/18 15:08
 * @description: MD5工具类
 */
public class MD5Utils {
    public static void main(String[] args) {
        String salt = getRandomSalt();
        String pwd = md5Hex("123456",salt);
        System.out.println(salt);
        System.out.println(pwd);
    }
    private static final String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * MD5加密
     *
     * @param plaintext 密码
     * @param salt      盐值
     * @return 密文
     */
    public static String md5Hex(String plaintext, String salt) {
        return DigestUtils.md5Hex(plaintext + salt);
    }

    /**
     * 获取64位的随机盐值
     */
    public static String getRandomSalt() {
        return getRandomSalt(64);
    }

    /**
     * 获取指定位数的随机盐值
     *
     * @param num 位数
     * @return 随机盐值
     */
    public static String getRandomSalt(final int num) {
        final StringBuilder saltString = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            saltString.append(B64T.charAt(new SecureRandom().nextInt(B64T.length())));
        }
        return saltString.toString();
    }
}
