package com.lanlin.jinshuiqi.testdemo;

import com.lanlin.jinshuiqi.service.UserService;

public class T1 {
    public static void main(String[] args) {
        UserService userService = new UserService();
        System.out.println(userService.findByUsername("minbad1"));
    }
}
