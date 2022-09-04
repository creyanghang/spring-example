package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @description: spring-webflux案例
 * @author: yh
 * @date: 2022/9/2
 */
@SpringBootApplication
@EnableWebFlux
public class SpringWebfluxSessionApplication implements WebFluxConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxSessionApplication.class, args);
    }

}

