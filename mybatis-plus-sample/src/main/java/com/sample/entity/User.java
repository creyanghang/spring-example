package com.sample.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: yh
 * @Description:
 * @date: 2022/6/6 11:45
 */
@Data
@Accessors(chain = true)
@TableName(value = "user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
