package com.lanlin.jinshuiqi.service;

import com.lanlin.jinshuiqi.dao.UserDao;
import com.lanlin.jinshuiqi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service

public class UserService {

    //私有化构造DAO层
    @Autowired
    private UserDao userDao;

    public User findById(Integer id){

        //调用DAO层通过用户ID查询获取单个用户并返回
        User user = this.userDao.findById(id);
        return user;

    }

    //开启事务  保存单个用户
    @Transactional
    public void save(User user){
       this.userDao.saveAndFlush(user);

    }

    //查询所有用户 以分页的形式返回结果

    public Page<User> findAll(Integer page){
       final  Integer size=10;//锁定一页固定显示5条数据
        Page<User> all = userDao.findAll(PageRequest.of(page, size));
        return all;


    }
    public List<User> getAll(){
        List<User> userList = userDao.findAll();
        return userList;
    }

    //更新操作
    @Transactional
    public void update(User user,Integer id){

    }
    @Transactional
    public void delById(Integer id){
        this.userDao.deleteById(id);

    }

    //根据用户名和密码查询


    public User findByUsername(String username ){
        User user = this.userDao.findByUsername(username);
        return user;
    }
}
