package com.resumer.annotation;

import java.lang.annotation.*;

/**
 * 免登录验证
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unimpeded {
}
