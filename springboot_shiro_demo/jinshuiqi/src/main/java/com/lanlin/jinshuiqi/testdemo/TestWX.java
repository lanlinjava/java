package com.lanlin.jinshuiqi.testdemo;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class TestWX {

    //对定义的微信的MD5算法和HMACSHA256算法进行测试
    public static void main(String[] args) {
        String s1= "lindong525";
        try {
            String s = MD5(s1);
            System.out.println(s);
            //d08d39864ff7863e4a6d53f1a624c371
            //D08D39864FF7863E4A6D53F1A624C371

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String s = HMACSHA256(s1, s1);
            System.out.println("HMACSHA256加密"+s);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
}
