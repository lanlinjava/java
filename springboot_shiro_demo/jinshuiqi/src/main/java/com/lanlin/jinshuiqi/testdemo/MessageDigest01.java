package com.lanlin.jinshuiqi.testdemo;



import java.io.UnsupportedEncodingException;
import java.util.Base64;


class MessageDigest01 {


    public static void main(String[] args) {
            String str="lindong525";
        try {
            byte[] bytes1 = str.getBytes("UTF-8");
            String newstr = Base64.getEncoder().encodeToString(str.getBytes());
            System.out.println(newstr);// 加密结果 bGluZG9uZzUyNQ==
//            Base64.Decoder decoder = Base64.getDecoder();
//            System.out.println(new String(decoder.decode(newstr), "UTF-8"));

            byte[] decode = Base64.getDecoder().decode(newstr);

            System.out.println(new String(decode));//解密 结果lindong525

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }





}
