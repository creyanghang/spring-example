package com.example.service;

import com.example.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 接口
 * @author:  yh
 * @date:  2022/9/2
 */
public interface IProductService {
    /**
     * 根据 id 查询用户
     *
     * @author: yh
     * @date: 2022/9/2
     */
    Mono<Product> getProductById(int id);

    /**
     * 根查询所有用户
     *
     * @author: yh
     * @date: 2022/9/2
     */
    Flux<Product> getAllProduct();

    /**
     * 添加用户
     *
     * @author: yh
     * @date: 2022/9/2
     */
    Mono<Void> saveProductInfo(Mono<Product> user);
}
