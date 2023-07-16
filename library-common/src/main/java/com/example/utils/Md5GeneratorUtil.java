package com.example.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/24 23:18
 */
public class Md5GeneratorUtil {
    private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static String getMd5String(String content) throws NoSuchAlgorithmException {
        StringBuilder stringBuilder = new StringBuilder("");
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        byte[] md5Bytes = messageDigest.digest(content.getBytes());

        int len = md5Bytes.length;
        System.out.println("md5的长度: "+len);
        for (int i = 0; i < len; i++) {
            stringBuilder.append(Integer.toHexString((md5Bytes[i] >> 4) & 0xf));
            stringBuilder.append(Integer.toHexString(md5Bytes[i] & 0xf));
        }
        return stringBuilder.toString();
    }

    public static String getMd5String(String content, String salt) throws NoSuchAlgorithmException {
        return getMd5String(getMd5String(content) + salt);
    }

    public static String encryptSpringMd5(String content) {
        return B_CRYPT_PASSWORD_ENCODER.encode(content);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String a = "mdp12334567fdsfds123456";
        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(a);

        while (matcher.find()) {
            System.out.println("数字第一次出现的索引是: "+matcher.start());
            System.out.println(matcher.group());
        }
    }
}
