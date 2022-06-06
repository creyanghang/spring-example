package com.mongo.controller;

import com.mongo.dao.UserRepository;
import com.mongo.pojo.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/test")
    public void test(){
        userRepository.deleteAll();

        List<UserPO> list = new ArrayList<>();
        list.add(new UserPO(1L, "didi", 30));
        list.add(new UserPO(2L, "mama", 40));
        list.add(new UserPO(3L, "kaka", 50));

        // 添加三个User
        userRepository.saveAll(list);

        // 删除一个User，再验证User总数
        userRepository.deleteById(1L);
        List<UserPO> all = userRepository.findAll();
        System.out.println(all.size());

        // 删除一个User，再验证User总数
        UserPO mama = userRepository.findByUsername("mama");
        System.out.println(mama);
    }
}
