package com.lanlin.jinshuiqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lanlin.jinshuiqi.*")
public class JinshuiqiApplication {

    public static void main(String[] args) {

        SpringApplication.run(JinshuiqiApplication.class, args);
    }

}
