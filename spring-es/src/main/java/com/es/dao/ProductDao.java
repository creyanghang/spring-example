package com.es.dao;

import com.es.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 将实现类的对象存到Spring容器中,实现基本的增删改查
 * @author  yh
 * @date  2022/6/3
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Long> {

    /**
     * 可以通过方法名来表达操作含义
     * @param price 商品价格
     * @author yh
     * @date 2022/6/3
     * @return Product
     */
    Iterable<Product> queryByPrice(Double price);


}