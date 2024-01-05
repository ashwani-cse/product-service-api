package com.app.fakestore.consumer.service;

import com.app.fakestore.consumer.model.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProductList();

    Product addProduct(Product dto);
}
