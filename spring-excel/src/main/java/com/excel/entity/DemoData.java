package com.excel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
//@Accessors(chain = true)  不要用这个注解
@AllArgsConstructor
@NoArgsConstructor
public class DemoData implements Serializable {
    /**
     * value	空	用于匹配excel中的头，必须全匹配,如果有多行头，会匹配最后一行头
     * order	Integer.MAX_VALUE	优先级高于value，会根据order的顺序来匹配实体和excel中数据的顺序
     * index	-1	优先级高于value和order，会根据index直接指定到excel中具体的哪一列
     */

    @ExcelProperty(value = "字符串标题")
    private String name;

    @ExcelProperty(value = "日期标题")
    private Date date;


    @ExcelProperty(value = "数字标题", index = 2)
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}