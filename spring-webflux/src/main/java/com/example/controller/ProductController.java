package com.example.controller;

import com.example.entity.Product;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: yh
 * @date: 2022/9/2
 */
@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * id 查询
     * @param id
     * @author: yh
     * @date: 2022/9/2
     * @return
     */
    @GetMapping("/product/{id}")
    public Mono<Product> getProductId(@PathVariable int id) {
        return productService.getProductById(id);
    }

    /**
     * @return
     */
    @GetMapping("/product")
    public Flux<Product> getProducts() {
        return productService.getAllProduct();
    }

    /**
     * @param Product
     * @return
     */
    @PostMapping("/saveProduct")
    public Mono<Void> saveProduct(@RequestBody Product Product) {
        Mono<Product> ProductMono = Mono.just(Product);
        return productService.saveProductInfo(ProductMono);
    }
}
