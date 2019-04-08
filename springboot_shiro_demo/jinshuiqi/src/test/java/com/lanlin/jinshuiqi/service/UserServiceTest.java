package com.lanlin.jinshuiqi.service;

import com.lanlin.jinshuiqi.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void test() {
        User user = this.userService.findByUsername("minbad1");
        System.out.println(user.toString());
    }
}