package com.lanlin.jinshuiqi.testdemo;


import org.apache.commons.codec.digest.DigestUtils;
public class Test3 {
    public static void main(String[] args) {
        String str= "lindong525" ;
            byte[] bytes = str.getBytes();
            String bytes1 = DigestUtils.md5Hex(bytes);
            //加密结果                        d08d39864ff7863e4a6d53f1a624c371
        //    查询结果： md5(lindong525,32) = d08d39864ff7863e4a6d53f1a624c371

        System.out.println(bytes1);
       // String str2="lindong525";


        //通过apache的加密工具类可以直接加密字符串 返回一个md5加密的字符串
        String s = DigestUtils.md5Hex("china520");
        String s2 = DigestUtils.md5Hex("123123aa");
        String s3 = DigestUtils.md5Hex("aa123123");
        String s4 = DigestUtils.md5Hex("lindong525");
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s4);

    }
}
