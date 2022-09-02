package com.example;

import com.example.handler.ProductHandler;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @description:
 * @author: yh
 * @date: 2022/9/2
 */
public class FluxServer {
    //1 创建 Router 路由
//    public RouterFunction<ServerResponse> routingFunction() {
//        //创建 hanler 对象
//        ProductHandler handler = new ProductHandler();
//        //设置路由
//        return RouterFunctions.route(
//                        GET("/product/{id}").and(accept(APPLICATION_JSON)), handler::getProductById)
//                .andRoute(GET("/product").and(accept(APPLICATION_JSON)), handler::getAllProducts)
//                .andRoute(POST("/saveProduct").and(accept(APPLICATION_JSON)), handler::saveProduct);
//    }
//
//    //2 创建服务器完成适配
//    public void createReactorServer() {
//        //路由和 handler 适配
//        RouterFunction<ServerResponse> route = routingFunction();
//        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
//        ReactorHttpHandlerAdapter adapter = new
//                ReactorHttpHandlerAdapter(httpHandler);
//        //创建服务器
//        HttpServer httpServer = HttpServer.create();
//        httpServer.handle(adapter).bindNow();
//    }
//
//    //最终调用
//    public static void main(String[] args) throws Exception {
//        FluxServer server = new FluxServer();
//        server.createReactorServer();
//        System.out.println("enter to exit");
//        System.in.read();
//    }
}
