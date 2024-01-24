package com.product.service.repository;

import com.product.service.model.Category;
import com.product.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    default Product saveProduct(Product product) {
        product.setCreateTimeStamp(Instant.now().toString());
        product.setUpdateTimeStamp(Instant.now().toString());
        return save(product);
    }

    default Product updateProduct(Product product) {
        product.setUpdateTimeStamp(Instant.now().toString());
        return save(product);
    }

    Optional<Product> findByTitleAndCategory(String name, Category category);

}
