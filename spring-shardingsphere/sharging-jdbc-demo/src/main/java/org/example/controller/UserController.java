package org.example.controller;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 读写分离测试，对应配置文件 application-dev.yml
 *
 * @author: yh
 * @date: 2022/9/18
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     SQL:
         CREATE DATABASE db_user;
         USE db_user;
         CREATE TABLE t_user (
         id BIGINT AUTO_INCREMENT,
         uname VARCHAR(30),
         PRIMARY KEY (id)
         );
         INSERT INTO t_user(uname) VALUES('zhang3');
         INSERT INTO t_user(uname) VALUES(@@hostname);
     */

    @Resource
    private UserMapper userMapper;

    /**
     * 插入数据，测试是否写入master
     * 如果开启事务，读会走master数据源
     * @author: yh
     * @date: 2022/9/18
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/insert")
    public void userInsert(){
        User u = new User();
        u.setUname("西门庆"+System.currentTimeMillis());
        userMapper.insert(u);
        List<User> users = userMapper.selectList(null);
        System.out.println(users.size());
    }

    /**
     * 查询
     * @author: yh
     * @date: 2022/9/18
     */
    @GetMapping("/selectAll")
    public void selectAll(){
        userMapper.selectList(null);
    }
}
