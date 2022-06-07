package com.Stream.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yh
 * @description 学生
 * @date 2022/6/7
 */
@Data
@Accessors
public class Student {

    private Integer id;

    private String name;

    private Integer age;
}
