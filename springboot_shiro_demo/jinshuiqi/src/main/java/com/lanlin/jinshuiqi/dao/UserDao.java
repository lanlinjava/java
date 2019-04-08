package com.lanlin.jinshuiqi.dao;

import com.lanlin.jinshuiqi.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDao extends JpaRepository<User, Long>  {
    //通过用户id查找单个用户 完成 并通过thymeleaf模板引擎来展示单用户信息
    User findById(Integer id);

    @Override
    <S extends User> S saveAndFlush(S s);

    //调用接口中的分布查询方法 传入一个分页对象进行分布查询
    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    List<User> findAll();

    void deleteById(Integer id);

    User findByUsername(String username);


}
