package com.es;

import com.es.dao.ProductDao;
import com.es.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yh
 * @description
 * @date 2022/6/3
 */
@SpringBootTest
public class EsProductApplicationTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 新增
     *
     * @author yh
     * @date 2022/6/3
     */
    @Test
    public void save() {
        Product product = new Product();
        product.setId(2L);
        product.setName("华为手机");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImages("http://www.image/hw.jpg");
        productDao.save(product);
        //GET http://localhost:9200/product/_doc/2
    }


    /**
     * 修改
     *
     * @author yh
     * @date 2022/6/3
     */
    @Test
    public void update() {
        Product product = new Product();
        product.setId(2L);
        product.setName("华为Pro");
        product.setCategory("手机");
        product.setPrice(9999.0);
        product.setImages("http://www.atguigu/xm.jpg");
        productDao.save(product);
    }

    /**
     * 根据 id 查询
     *
     * @author yh
     * @date 2022/6/3
     */
    @Test
    public void findById() {
        Product product = productDao.findById(2L).get();
        System.out.println(product);
    }

    /**
     * 查询所有
     *
     * @author yh
     * @date 2022/6/3
     */
    @Test
    public void findAll() {
        Iterable<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
        // GET: http://localhost:9200/product/_search
    }

    /**
     * 指定id删除文档
     *
     * @author yh
     * @date 2022/6/3
     */
    @Test
    public void delete() {
        Product product = new Product();
        product.setId(2L);
        productDao.delete(product);
    }

    /**
     * 批量新增
     *
     * @author yh
     * @date 2022/6/3
     */
    @Test
    public void saveAll() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setName("小米手机[" + i + "]");
            product.setCategory("手机");
            product.setPrice(1999.0 + i);
            product.setImages("http://www.image/xm.jpg");
            productList.add(product);
        }
        productDao.saveAll(productList);
    }

    /**
     * 分页查询
     * @author  yh
     * @date  2022/6/3
     */
    @Test
    public void findByPageable(){
        // 设置排序(排序方式，正序还是倒序，排序的 id)
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        // 当前页，第一页从 0 开始， 1 表示第二页
        int currentPage=0;
        // 每页显示多少条
        int pageSize = 5;
        // 设置查询分页
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize,sort);
        // 分页查询
        Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product Product : productPage.getContent()) {
            System.out.println(Product);
        }
    }

    @Test
    public void queryByName(){
        Iterable<Product> items = productDao.queryByPrice(new Double(2006));
        for (Product item : items) {
            System.out.println(item);
        }
    }
}
