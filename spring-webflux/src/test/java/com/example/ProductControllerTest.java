package com.example;

import io.netty.handler.codec.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yh
 * @date: 2022/9/2
 */
public class ProductControllerTest {
    @Test
    public void getProductId() {
        String url = urlLocal + "/product/11";
        System.out.println("请求地址:" + url);
        HttpRequest request = HttpUtil.createRequest(Method.GET, url);
        request.setConnectionTimeout(60 * 1000);
        String response = request.execute().body();
        System.out.println("响应结果:" + response);
    }

    @Test
    public void getProducts() {
        String url = urlLocal + "/product";
        System.out.println("请求地址:" + url);
        HttpRequest request = HttpUtil.createRequest(Method.GET, url);
        request.setConnectionTimeout(60 * 1000);
        String response = request.execute().body();
        System.out.println("响应结果:" + response);
    }

    @Test
    public void saveProduct() {
        String url = urlLocal + "/saveProduct";
        System.out.println("请求地址:" + url);
        HttpRequest request = HttpUtil.createRequest(Method.POST, url);
        Map<String, Object> map = new HashMap<>();
        map.put("productNo", "P203");
        map.put("productName", "习惯03");
        String jsonStr = JSON.toJSONString(map);
        request.body(jsonStr);
        System.out.println("请求参数:" + jsonStr);
        request.setConnectionTimeout(60 * 1000);
        String response = request.execute().body();
        System.out.println("响应结果:" + response);
    }
}
