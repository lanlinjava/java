package com.lanlin.jinshuiqi.controller;

import com.lanlin.jinshuiqi.domain.User;
import com.lanlin.jinshuiqi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;




@Controller
public class UserControllers {
    @Autowired
    private UserService userService;


    //显示指定ID用户的信息
    @RequestMapping(value="/list{id}",method = RequestMethod.GET)
    public String show1(@RequestParam("id") Integer id, Model model){
        User user = userService.findById(id);
        model.addAttribute("u",user);
         return "show";
    }
    //添加用户 通过封装post 数据实现
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(HttpServletRequest request , HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf8");
            String username = request.getParameter("username");
            String userpwd = request.getParameter("userpwd");
            String md5pwd = DigestUtils.md5Hex(userpwd);
            User user = new User();
            user.setUsername(username);
            user.setUserpwd(md5pwd);
            userService.save(user);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "saveok";
    }

    //实现用户的查询结果分页
    @RequestMapping(value = "/listall{page}",method = RequestMethod.GET)
    public String listAll ( @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "5") Integer size, Model model){
        //判断分页数 如果小于0则初始化成0就是第一页 注意JPA 默认分页从0开始为第一页
        //如果页数大于总页数则直接定位到最后一页
        if(page<0){
            page=0;
        };
        Page<User> userPage = userService.findAll(page);
        List<User> userList = userService.findAll(page).getContent();
        int totalPages = userPage.getTotalPages();
        int rspages= totalPages-1;


        long totalElements = userPage.getTotalElements();
//        System.out.println("总记录数"+totalElements);
//        System.out.println("总页数"+totalPages);
        model.addAttribute("userList",userList);
        model.addAttribute("rspages",rspages);
        model.addAttribute("totalElements",totalElements);
        model.addAttribute("page",page);
        return "showall";
    }

    //实现用户的按ID显示 并进行资料修改
    @RequestMapping(value = "/checkid{id}",method = RequestMethod.GET )
    public String checkid(@RequestParam(value = "id") Integer id,Model model){
        User user = userService.findById(id);
        model.addAttribute(user);
        return  "checkid";
    }

    //实现用户资料修改并提交保存
    @RequestMapping(value = "/update" )
    public String update(HttpServletRequest request ,Model model)throws Exception {
            request.setCharacterEncoding("UTF-8");
            User user =new User();
        String uid = request.getParameter("id");
       Integer id = Integer.parseInt(uid);
        String username = request.getParameter("username");
       String userpwd = request.getParameter("userpwd");
       String usermobile =  request.getParameter("usermobile");
       String useraddr =  request.getParameter("useraddr");
        String userfirstinstall = request.getParameter("userfirstinstall");
        String usernextservice = request.getParameter("usernextservice");



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
           Date userfirstinstall2 = sdf.parse(userfirstinstall);
           Date usernextservice2 = sdf.parse(usernextservice);
        System.out.println("提交过来的首次安装时间参数格式化后的值"+userfirstinstall2.toString());
        System.out.println("提交过来的下次参数格式化的值"+usernextservice2.toString());

        user.setId(id);
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setUsermobile(usermobile);
        user.setUseraddr(useraddr);
        user.setUserfirstinstall( userfirstinstall2);
        user.setUsernextservice( usernextservice2);
        user.setUserinforemark(request.getParameter("userinforemark"));
        userService.save(user);
        model.addAttribute("user",user);

        return "updateok";
    }

    //根据ID删除用户

    @RequestMapping(value = "delbyid{id}",method = RequestMethod.GET )
    @ResponseBody
    public String del(@RequestParam(value = "id") Integer id){
        this.userService.delById(id);
        return "ok";
    }
}

