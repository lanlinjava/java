package com.lanlin.jinshuiqi.controller;

import com.lanlin.jinshuiqi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用shiro进行认证的controller
 * 1 第一步通过SecurityUtils的工具类的静态方法 getSubject()方法 获取shiro的subject
 * 2 第二步 封装用户的数据 获取token  使用 UsernamePasswordToken
 *
 */

@Controller
public class loginShiroController {

    @Autowired
   private UserService userService;

    @RequestMapping("/dologin")
    public String toLogin(@RequestParam("username") String username, @RequestParam("userpwd")  String userpwd , Model model){

        // 1 通过SecurityUtils的工具类的静态方法 getSubject()方法 获取shiro的subject
        Subject subject = SecurityUtils.getSubject();

        //2 把用户提交的数据包装成 token
        UsernamePasswordToken token = new UsernamePasswordToken(username, userpwd);
        // 设置允许使用记住我的功能 可以不设置  不是必需的
        token.setRememberMe(true);

        //3 通过获取用户的subject主体 并执行shiro的认证 需要传入一个tokey的参数
        //注意 可以使用try catch 抓取认证异常 任何的异常都是说明登陆认证失败
        //执行shiro登陆的逻辑 注意 该方法的执行 就会执行shiro的Realm 认证权限判定
        try {
            subject.login(token);
            //如果没有抛出异常 则返回正常登陆 请求转发到后台的地址
            return "listall";

        }catch(UnknownAccountException e )//用户名不存在找不到异常
         {
             //e.printStackTrace();
            //登陆用户名不存在认证失败
             model.addAttribute("msg","用户名不存在");
             return "relogin";// 如果使用return "redirect:relogin" 提示的错误信息无法通过重定向传递 所以使用直接转发请求
        }catch(IncorrectCredentialsException e){//密码认证失败抛出异常
            model.addAttribute("msg","用户名或密码不匹配");
            return "relogin";

        }




    }
}
