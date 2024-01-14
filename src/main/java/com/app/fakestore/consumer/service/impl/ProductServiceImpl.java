package com.app.fakestore.consumer.service.impl;

import com.app.fakestore.consumer.constant.Constants;
import com.app.fakestore.consumer.exception.ApiException;
import com.app.fakestore.consumer.exception.ObjectNotFoundException;
import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.model.Product;
import com.app.fakestore.consumer.repository.CategoryRepository;
import com.app.fakestore.consumer.repository.ProductRepository;
import com.app.fakestore.consumer.service.ProductService;
import com.app.fakestore.consumer.util.ApplicationUtil;
import com.app.fakestore.consumer.util.ProductFieldMapper;
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

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            log.error("Product doesn't exist in database.");
            throw new ObjectNotFoundException(Constants.Product.NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public List<Product> getAllProductList() {
        log.info("Getting all product list");
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        Category category = categoryRepository.findCategoryByName(product.getCategory().getName());
        product.setCategory(category);
        UUID uuid = UUID.randomUUID();
        product.setProductUUID(uuid.toString());
        return productRepository.saveProduct(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> prodOptional = productRepository.findById(id);
        if (prodOptional.isEmpty()) {
            log.error("Product not found. Adding new product: {}", product);
            return addProduct(product);
        } else {
            Product dbProduct = prodOptional.get();
            product.setCategory(checkAndReturnCategory(product));
            product.setProductUUID(dbProduct.getProductUUID());
            log.info("Updating product: {}", product);
            return productRepository.updateProduct(product);
        }
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> prodOptional = productRepository.findById(id);
        if (prodOptional.isEmpty()) {
            String errorMessage = String.format("Product doesn't exist in the database for id: %d", id);
            log.error(errorMessage);
            throw new ObjectNotFoundException(errorMessage);
        } else {
            Product dbProduct = prodOptional.get();
            product.setCategory(checkAndReturnCategory(product));
            ProductFieldMapper.mapProduct(product, dbProduct);
            log.info("Updating product: {}", dbProduct);
            return productRepository.updateProduct(dbProduct);
        }
    }

    private Category checkAndReturnCategory(Product product) {
        Category category = product.getCategory();
        if (category != null && category.getName() != null)
            category = categoryRepository.findByName(product.getCategory().getName())
                    .orElseThrow(() -> new ObjectNotFoundException("Invalid Category"));
        return category;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
