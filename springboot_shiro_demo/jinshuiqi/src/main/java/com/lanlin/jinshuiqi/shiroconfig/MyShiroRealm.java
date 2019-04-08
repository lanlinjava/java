package com.lanlin.jinshuiqi.shiroconfig;

import com.lanlin.jinshuiqi.domain.User;
import com.lanlin.jinshuiqi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 重写shiro中认证和权限的具体的逻辑方法
 *  Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。
 *  也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。
 * 从这个意义上讲，Realm实质上是一个安全相关的DAO：它封装了数据源的连接细节，并在需要时将相关数据提供给Shiro。
 * 当配置Shiro时，你必须至少指定一个Realm，用于认证和（或）授权。配置多个Realm是可以的，但是至少需要一个。
 * Shiro内置了可以连接大量安全数据源（又名目录）的Realm，如LDAP、关系数据库（JDBC）、类似INI的文本配置资源以及属性文件等。
 * 如果缺省的Realm不能满足需求，你还可以插入代表自定义数据源的自己的Realm实现。
 */

public class MyShiroRealm extends AuthorizingRealm {

    //私有化service 方便从数据库中取出对应的用户认证和授权数据 注意要带上【注解自动装配 autowired】
    @Autowired
    private UserService userService;
    //该方法执行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }



    //该方法执行认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        System.out.println(username);
        User getUser = this.userService.findByUsername(username);
        if (getUser==null){
            //判断通过用户提交的用户名进行数据库查找 如果找不到 就是用户名不存在返回一个null
            return null;//shiro在底层就会相对应的抛出一个 UnknownAccountException e 用户名不存在异常

        }
        //2 shiro会自动执行密码判断
        return new SimpleAuthenticationInfo("",getUser.getUserpwd(),"");
    }
}
