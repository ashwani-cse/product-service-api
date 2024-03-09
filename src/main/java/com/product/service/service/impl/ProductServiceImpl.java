package com.product.service.service.impl;

import com.product.service.constant.Constants;
import com.product.service.enums.SortableFields;
import com.product.service.exception.BadRequestException;
import com.product.service.exception.ObjectNotFoundException;
import com.product.service.model.Category;
import com.product.service.model.Product;
import com.product.service.repository.CategoryRepository;
import com.product.service.repository.ProductRepository;
import com.product.service.service.ProductService;
import com.product.service.util.ProductFieldMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<Product> getAllProductList(int page, int size, String sortBy, String order) {
        log.info("Getting all product list with pagination and sorting. Page: {}, Size: {}, SortBy: {}, Order: {}", page, size, sortBy, order);
        Sort sort = getSort(sortBy, order);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    private Sort getSort(String sortBy, String order) {
//        Sort sort = Sort.by(sortBy).descending();
//        Sort sort =  Sort.by("price").ascending()
//               .and(Sort.by("name").descending())
//               .and(Sort.by("category").ascending());

        try {
            SortableFields.valueOf(sortBy.toUpperCase());
            Sort.Direction direction = Sort.Direction.valueOf(order.toUpperCase());
            return Sort.by(direction, sortBy);
        } catch (IllegalArgumentException e) {
            log.error("Invalid sortBy: {} or orderBy: {}", sortBy, order);
            throw new BadRequestException("Invalid sortBy or order field");
        }
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
        productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product doesn't exist in database"));
        productRepository.deleteById(id);
    }
}
