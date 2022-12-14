package com.Stream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author yh
 * @description 学生
 * @date 2022/6/7
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student{

    private Integer id;

    private String name;

    private Integer age;

    /**
     * 班级id
     * @param null
     * @author yh
     * @date 2022/6/8
     */

    private Integer clazzId;
}
