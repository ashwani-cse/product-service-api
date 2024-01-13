package com.app.fakestore.consumer.service.impl;

import com.app.fakestore.consumer.constant.Constants;
import com.app.fakestore.consumer.exception.ApiException;
import com.app.fakestore.consumer.exception.ObjectNotFoundException;
import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.model.Product;
import com.app.fakestore.consumer.repository.CategoryRepository;
import com.app.fakestore.consumer.repository.ProductRepository;
import com.app.fakestore.consumer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ashwani Kumar
 * Created on 13/01/24.
 */

@Slf4j
@Primary
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty()){
            log.error("Product doesn't exist in database.");
            throw new ObjectNotFoundException(Constants.PRODUCT_NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public List<Product> getAllProductList() {

        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        Product obj;
        Optional<Category> optional = categoryRepository.findByName(product.getCategory().getName());
        if (optional.isEmpty()){
            log.error("Category not found.");
            throw new ObjectNotFoundException(Constants.CATEGORY_NOT_EXIST);
        }
        try {
            product.setCategory(optional.get());
            UUID uuid = UUID.randomUUID();
            product.setProductUUID(uuid.toString());
            obj = productRepository.save(product);
        } catch (Exception e) {
            log.error("Error occurred while saving product in database.");
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return obj;
    }

    @Override
    public Product updateProduct(Long id, Product dto) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product dto) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
