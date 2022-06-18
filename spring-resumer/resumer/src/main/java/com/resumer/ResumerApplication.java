package com.resumer;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description
 * @auth yh
 * @date 2022/5/29
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan("com.resumer.mapper")
public class ResumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumerApplication.class, args);
    }
}