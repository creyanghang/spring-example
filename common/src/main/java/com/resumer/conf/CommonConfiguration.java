package com.resumer.conf;

import com.resumer.aspect.ControllerLogAspect;
import com.resumer.interceptor.ResumerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description
 * @auth yh
 * @date 2022/5/29
 */
public class CommonConfiguration {

    /**
     * 綫程池配置
     */
    @Bean
    public TaskExecutorConfig getTaskExecutorConfig() {
        return new TaskExecutorConfig();
    }

    /**
     * http 请求
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean("ResumerInterceptor")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "common.setting.intercept", name = "type", havingValue = "resumer")
    public ResumerInterceptor getRResumerInterceptor() {
        return new ResumerInterceptor();
    }

    @Bean("ResumerInterceptorConfig")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "common.setting.intercept", name = "type", havingValue = "resumer")
    public ResumerInterceptorConfig getUserInterceptorConfig() {
        return new ResumerInterceptorConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public ControllerLogAspect getControllerLogAspect() {
        return new ControllerLogAspect();
    }
}