package com.Stream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author yh
 * @description 班级
 * @date 2022/6/7
 */
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {

    private Integer id;

    private String name;

    /**
     * 学生id
     * @param null
     * @author yh
     * @date 2022/6/8
     */

    private List<Integer> studentId;
}
