package com.lanlin.jinshuiqi.shiroconfig;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 使用的流程和步骤
 * 1 设置安全拦截规则  shiroFilterFactoryBean
 * 2 获取安全管理器 SecurityManager
 * 3 获取subject
 * 4 获取验证授权服务 ShiroRealm
 */

@Configuration
public class JsqShiroConfig  {
    // 创建Shiro认证授权过滤器
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager securityManager ){

        //通过ShiroFilterFactoryBean类直接new 一个对象出来;
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器注入到shiro的过滤器链 实现过滤认证授权
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //使用MAP集合来实现过滤器链 key 是要过链的连接地址  而value是shiro中授权的值 定义如下
        /**
         * 配置拦截器
         *
         * 定义拦截URL权限，优先级从上到下
         * 1). anon  : 匿名访问，无需登录
         * 2). authc : 登录后才能访问
         * 3). logout: 登出
         * 4). roles : 角色过滤器
         * 5). user :如果使用rememberme 功能可以直接使用
         * 6). perms : 资源必需要得到授权才能访问
         *
         * URL 匹配风格
         * 1). ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/；
         * 2). *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1；
         * 2). **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
         *
         * 配置身份验证成功，失败的跳转路径
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/static/**", "anon");    // 静态资源匿名访问
        filterChainDefinitionMap.put("/login.html", "anon");       //放行登陆页面的请求
        filterChainDefinitionMap.put("/dologin", "anon");       //放行登陆页面的请求
        filterChainDefinitionMap.put("/save*", "authc"); //保存用户请求需要认证才能操作
        filterChainDefinitionMap.put("/listall*", "authc"); //查看所有用户数据 也需要认证才能操作
        filterChainDefinitionMap.put("/checkid*", "authc"); //查看所有用户数据 也需要认证才能操作
        // filterChainDefinitionMap.put("/employees/login", "anon");// 登录匿名访问
      //  filterChainDefinitionMap.put("/logout", "logout");    // 用户退出，只需配置logout即可实现该功能
       //filterChainDefinitionMap.put("/**", "authc");        // 其他路径均需要身份认证，一般位于最下面，优先级最低
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
       shiroFilterFactoryBean.setLoginUrl("/login.html");        // 登录的路径
       // shiroFilterFactoryBean.setSuccessUrl("/dashboard");    // 登录成功后跳转的路径
      //  shiroFilterFactoryBean.setUnauthorizedUrl("/403");    // 验证失败后跳转的路径


        return  shiroFilterFactoryBean;
    }

    //
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        return securityManager;



    }


}
