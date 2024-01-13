package com.app.fakestore.consumer.repository;

import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByTitleAndCategory(String name, Category category);

}
