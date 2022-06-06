package com.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * <br>
 * created date 2022/3/20 17:10
 *
 * @author yh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
// // shards = 3 表示分片数量，其中副本replicas数量为1
@Document(indexName = "product", shards = 3, replicas = 1)
public class Product {
    /**
     * 必须有 id,这里的 id 是全局唯一的标识，等同于 es 中的"_id"
     * 商品唯一标识
     * @author  yh
     * @date  2022/6/3
     */
    @Id
    private Long id;

    /**
     * type : 字段数据类型
     * analyzer : 分词器类型 analyzer = "ik_max_word"
     * index : 是否索引(默认:true)
     * Keyword : 短语,不进行分词
     */

    /**
     * 商品名称
     *
     * @author yh
     * @date 2022/6/3
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 价格
     *
     * @author yh
     * @date 2022/6/3
     */
    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 商品分类
     *
     * @author yh
     * @date 2022/6/3
     */
    @Field(type = FieldType.Keyword)
    private String category;

    /**
     * 图片地址
     *
     * @author yh
     * @date 2022/6/3
     */
    @Field(type = FieldType.Keyword, index = false)
    private String images;

    /**
     @Field(type=FieldType.Text, analyzer=“ik_max_word”) 表示该字段是一个文本，并作最大程度拆分，默认建立索引

     @Field(type=FieldType.Text,index=false) 表示该字段是一个文本，不建立索引

     @Field(type=FieldType.Date) 表示该字段是一个文本，日期类型，默认不建立索引

     @Field(type=FieldType.Long) 表示该字段是一个长整型，默认建立索引

     @Field(type=FieldType.Keyword) 表示该字段内容是一个文本并作为一个整体不可分，默认建立索引

     @Field(type=FieldType.Float) 表示该字段内容是一个浮点类型并作为一个整体不可分，默认建立索引
     */

}
