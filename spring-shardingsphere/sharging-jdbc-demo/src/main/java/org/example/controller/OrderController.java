package org.example.controller;

import org.example.entity.Order;
import org.example.entity.User;
import org.example.mapper.OrderMapper;
import org.example.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @description: 垂直分片
 * @author: yh
 * @date: 2022/9/18
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    /**
     SQL DDL:
             CREATE DATABASE db_user;
             USE db_user;
             CREATE TABLE t_user (
             id BIGINT AUTO_INCREMENT,
             uname VARCHAR(30),
             PRIMARY KEY (id)
             );

             CREATE DATABASE db_order;
             USE db_order;
             CREATE TABLE t_order (
             id BIGINT AUTO_INCREMENT,
             order_no VARCHAR(30),
             user_id BIGINT,
             amount DECIMAL(10,2),
             PRIMARY KEY(id)
             );
     */
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 垂直分片：插入数据测试
     * @author: yh
     * @date: 2022/9/18
     */
    @GetMapping(value = "/add")
    public void testInsertOrderAndUser(){

        User user = new User();
        user.setUname("小明");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("ATGUIGU001");
        order.setUserId(user.getId());
        order.setAmount(new BigDecimal(100));
        orderMapper.insert(order);

    }

    /**
     * 垂直分片：查询数据测试
     * @author: yh
     * @date: 2022/9/18
     */
    @GetMapping("/get")
    public void testSelectFromOrderAndUser(){
        User user = userMapper.selectById(1L);
        Order order = orderMapper.selectById(1L);
    }
}
