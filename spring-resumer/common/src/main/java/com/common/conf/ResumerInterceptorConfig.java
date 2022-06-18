package com.common.conf;

import com.common.interceptor.ResumerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @description 配置拦截器
 * @auth yh
 * @date 2022/5/28
 */
public class ResumerInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private ResumerInterceptor resumerInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resumerInterceptor).addPathPatterns("/**")
                .excludePathPatterns(Arrays.asList("/v2/api-docs",
                        "/doc.html",
                        "/error",
                        "/swagger-resources",
                        "/swagger-resources/configuration/ui"));
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}