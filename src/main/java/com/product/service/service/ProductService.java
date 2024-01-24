package com.product.service.service;

import com.product.service.model.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProductList();

    Product addProduct(Product dto);

    Product updateProduct(Long id, Product dto);

    Product replaceProduct(Long id, Product dto);

    void deleteProduct(Long id);
}
