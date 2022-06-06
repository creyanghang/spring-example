package com.redis.controller;

import com.redis.entity.UserVO;
import com.redis.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RedisCache redisCache;

    /**
     * 写入redis
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/set")
    public void setRedis() {
        UserVO vo = new UserVO();
        vo.setId(1001L);
        vo.setName("小明");
        vo.setEmail("123@126.com");

        redisCache.set("user:1001", vo, 60, TimeUnit.SECONDS);
        System.out.println("set redis success");
    }

    /**
     * 读取redis
     *
     * @author yh
     * @date 2022/6/5
     */
    @RequestMapping("/get")
    public void getRedis() {
        UserVO vo = redisCache.getValue("user:1001");
        System.out.println(vo);
    }
}
