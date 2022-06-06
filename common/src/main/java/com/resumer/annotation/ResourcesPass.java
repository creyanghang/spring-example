package com.resumer.annotation;


import java.lang.annotation.*;

/**
 * 不过滤权限验证
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResourcesPass {
}
