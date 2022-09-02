package com.example.handler;

import com.example.entity.Product;
import com.example.service.IProductService;
import com.example.service.impl.ProductServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: yh
 * @date: 2022/9/2
 */
public class ProductHandler {
    private IProductService productService;

    public ProductHandler() {
        this.productService = new ProductServiceImpl();
    }

    //根据 id 查询
    public Mono<ServerResponse> getProductById(ServerRequest request) {
        //获取 id 值
        int ProductId = Integer.valueOf(request.pathVariable("id"));
        //空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        //调用 service 方法得到数据
        Mono<Product> productMono = this.productService.getProductById(ProductId);
        //把 ProductMono 进行转换返回
        //使用 Reactor 操作符 flatMap
        return
                productMono
                        .flatMap(person ->
                                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                        .body(fromObject(person)))
                        .switchIfEmpty(notFound);
    }

    //查询所有
    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        //调用 service 得到结果
        Flux<Product> products = this.productService.getAllProduct();
        return
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(products, Product.class);
    }

    //添加
    public Mono<ServerResponse> saveProduct(ServerRequest request) {
        //得到 Product 对象
        Mono<Product> ProductMono = request.bodyToMono(Product.class);
        return
                ServerResponse.ok().build(this.productService.saveProductInfo(ProductMono));
    }
}
