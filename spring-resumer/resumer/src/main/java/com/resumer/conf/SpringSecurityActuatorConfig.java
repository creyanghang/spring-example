package com.resumer.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yh
 * @description
 * @date 2022/6/18
 */
@Slf4j
public class SpringSecurityActuatorConfig extends WebSecurityConfigurerAdapter {
    /*
     * 这个表单和HttpBasic 共存配置玩法，参考url如下：
     *  官方url：https://docs.spring.io/spring-security/site/docs/4.2.3.BUILD-SNAPSHOT/reference/htmlsingle/#multiple-httpsecurity
     *  项目启动日志如下,可以看到创建了2条过滤链
     * 2020-11-11 22:57:56.340  INFO 12692 --- [main] o.s.s.web.DefaultSecurityFilterChain: Creating filter chain: Ant [pattern='/actuator/**'],
     * 2020-11-11 22:57:56.344  INFO 12692 --- [main] o.s.s.web.DefaultSecurityFilterChain: Creating filter chain: any request,
     */
    /**
     * HttpBasic 认证方式，只对/actuator/** 生效，由于设置了Order，优先级会高于FormLoginWebSecurityConfigurerAdapter
     * @author ZENG.XIAO.YAN
     * @Date 2020-11-11
     * @version 1.0
     */
    @Configuration
    @Order(1)
    public static class HttpBasicSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        public HttpBasicSecurityConfigurationAdapter() {
            log.info("HttpBasicSecurityConfigurationAdapter... start");
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

    /**
     * 表单登录认证方式配置，由于没有指定Order，所以默认是最大2147483647，数值越大，优先级越低
     * @author ZENG.XIAO.YAN
     * @Date 2020-11-11
     * @version 1.0
     */
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        public FormLoginWebSecurityConfigurerAdapter() {
            log.info("FormLoginWebSecurityConfigurerAdapter... start");
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest()
                    .permitAll()
//                    .authenticated()
                    .and()
                    // druid配置
                    .csrf().ignoringAntMatchers("/druid/*");
//                    .formLogin();
        }
    }

}
