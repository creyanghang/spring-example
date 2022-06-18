package com.common.conf;


import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;


/**
 * @description 校验controller参数配置
 * @auth yh
 * @date 2022/5/25
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public static Validator validator() {
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                //开启快速校验，默认校验所有参数，false校验全部
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        //设置validator模式为快速失败返回
        processor.setValidator(validator());
        return processor;
    }
}