package com.lanlin.jinshuiqi.controller;

import com.lanlin.jinshuiqi.domain.User;
import com.lanlin.jinshuiqi.service.UserService;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


//@Controller
public class loginController {
//
//    @Autowired
//   private UserService userService;
//
//    @RequestMapping("/login")
//    public String toLogin(String username, String userpwd , Model model){
//        User user = this.userService.findByUsername(username);
//        if (user==null){
//            String msg="用户名不存在!请重新登陆";
//            model.addAttribute("msg",msg);
//            return "relogin";
//
//        }if(user!=null){
//            String userpwd1 = user.getUserpwd();
//            if (userpwd.equalsIgnoreCase(userpwd1)){
//                model.addAttribute("username",username);
//            }
//
//        }
//
//      return "listall";
//
//    }
}
