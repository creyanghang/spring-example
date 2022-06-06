package com.admin.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Slf4j
public class SpringSecurityActuatorConfig extends WebSecurityConfigurerAdapter {
    public SpringSecurityActuatorConfig() {
        log.info("SpringSecurityActuatorConfig... start");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  这个配置只针对  /actuator/** 的请求生效
        http.antMatcher("/actuator/**")
                // /actuator/下所有请求都要认证
                .authorizeRequests().anyRequest().authenticated()
                // 启用httpBasic认证模式，当springboot admin-client 配置了密码时，
                // admin-server走httpbasic的认证方式来拉取client的信息
                .and().httpBasic()
                // 禁用csrf
                .and().csrf().disable();
    }
}