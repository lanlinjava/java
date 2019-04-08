package com.lanlin.jinshuiqi.testdemo;

import org.apache.commons.codec.binary.Hex;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test00 {
    public static void main(String[] args) {
        //需要加密的字符串
        jiami("I am Summer!");
    }
    public static void jiami(String str){
        try {
            /**
             * MessageDigest 支持的摘要算法主要有以下几种
             * 1 MD5 加密 速度快
             * 2 SHA方式加密 速度慢 具体还有SHA的算法子类如下
             * 2.1 SHA1 加密  2.2 SHA-224  2.3 SHA-384  2.4 SHA-256 2.5 SHA-512
             */
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //这里的摘要算法可以按以上的取值获取不同的算法对象

            md5.update(str.getBytes("UTF-8"));//指定字符编码避免中文乱码
            byte[] digest = md5.digest();//提交进行算法加密

            //加密后的结果转换成16进制才能显示输出 否则为乱码
            System.out.println(Hex.encodeHexString(digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
