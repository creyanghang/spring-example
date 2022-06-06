package com.es;

import com.es.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.lang.annotation.Annotation;

/**
 * @author yh
 * @description https://blog.csdn.net/weixin_43847283/article/details/123616890
 * @date 2022/6/3
 */
@SpringBootTest
public class EsApplicationTest {

    //注入 ElasticsearchRestTemplate，也可以通过dao层操作索引
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索引并增加映射配置
     * 系统初始化会自动创建索引
     * @author  yh
     * @date  2022/6/3
     */
    @Test
    public void createIndex(){
        System.out.println("创建索引");
        //查看 GET: http://localhost:9200/_cat/indices?v
    }

    /**
     * 删除所以
     * @author yh
     * @date 2022/6/3
     */
    @Test
    public void deleteIndex(){
        Annotation documentAnnotation = Product.class.getDeclaredAnnotation(Document.class);
        if (documentAnnotation != null) {
            //获取索引库名称
            String indexName = ((Document) documentAnnotation).indexName();
            //索引是否存在
            IndexOperations indexOps = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
            //判断索引是否存在
            if (indexOps.exists()) {
                //删除索引
                indexOps.delete();
            }
        }
    }


}
