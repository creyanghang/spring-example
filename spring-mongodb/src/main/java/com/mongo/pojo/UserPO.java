package com.mongo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
@Data
@AllArgsConstructor
public class UserPO {
    @Id
    private Long id;

    private String username;
    private Integer age;
}

