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
 * @description: 访问层
 * @author: yh
 * @date: 2022/9/18
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    /**
     * 插入数据，测试是否写入master
     * 如果开启事务，读会走master数据源
     * @author: yh
     * @date: 2022/9/18
     */
    @Transactional
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
