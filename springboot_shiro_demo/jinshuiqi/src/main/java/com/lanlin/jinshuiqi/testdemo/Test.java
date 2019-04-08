package com.lanlin.jinshuiqi.testdemo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

class Test {
    public static void main(String[] args) {
        /**
         *  使用异或加解密 可逆的加解密
         * 需要一个异或的密文来加密和解密  如果知道这个密文就能随意进行加解密的逆向
         */
        //申明一个字符串
        String str="lindong525";
        //通过字符串的toCharArray方法转换成一个字符串数组
        char[] chars = str.toCharArray();
        //进行加解密一般都是要转换成字节数组
            //开始转换成字节数组
            byte[] bytes = str.getBytes();
            //通过for循环来遍历字节数组并同时使用异或进行加密
            for (int i = 0; i <chars.length ; i++) {
               // System.out.println(chars[i]); 遍历字符串数组
               chars [i] = (char) (chars[i] ^ 5);
            }
        //System.out.println(new String(chars)); //加密后的结果 ilkajkb070 异或密文6
        System.out.println(new String(chars)); //加密后的结果 fcdnedm?8?  异或密文6
        // 再次进行同样的异或运算就是解密 所以密文是解密的关键针对异或加密和解密
        for (int i = 0; i <chars.length ; i++) {
            chars [i] = (char) (chars[i] ^ 5);
        }
        System.out.println(chars);

    }

}
