package com.kafka;

import cn.hutool.core.date.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * kafka示例
 * @author  yh
 * @date  2022/6/3
 */
@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        System.out.println("springboot-start" + DateUtil.date());
        SpringApplication.run(KafkaApplication.class, args);
    }
}
