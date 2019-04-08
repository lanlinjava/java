package com.lanlin.jinshuiqi.testdemo;

import org.springframework.util.DigestUtils;
/**
 * MD5通用类
 * 使用springframework提供的MD5工具类来实现MD5算法的加密和解密
 * @author lanlin
 * @since 2018.03.26
 * @version 0.0.1
 */
public class Test2 {
    public static void main(String[] args) {
        String str="lindong525";
        byte[] bytes = str.getBytes();
        String s = DigestUtils.md5DigestAsHex(bytes);

        System.out.println(new String(s));

  /** 实现加密后的结果          d08d39864ff7863e4a6d53f1a624c371
         * md5(lindong525,32) = d08d39864ff7863e4a6d53f1a624c371 在线查出的基于MD5 32位加密 和实际加密的结果一样
         * md5(lindong525,16) = 4ff7863e4a6d53f1
         */


    }
}
