package com.app.fakestore.consumer.service;

import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Ashwani Kumar
 * Created on 14/01/24.
 */

@SpringBootTest
public class ProductServiceTest {
    
    @Autowired
    ProductService productService;
    
   // @Test
    void getSingleProduct(){
        Product product = productService.getSingleProduct(1L);
        Category category = product.getCategory();
        String name = category.getName();
    }

    @Test
    void getProducts(){
        List<Product> allProductList = productService.getAllProductList();
        allProductList.forEach(System.out::println);
    }
}
