package com.product.service.repository;

import com.product.service.constant.Constants;
import com.product.service.exception.ObjectNotFoundException;
import com.product.service.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    Optional<Category> findByName(String name);

    default Category findCategoryByName(String name) {
        Optional<Category> optional = findByName(name);
        if (optional.isEmpty()) {
            String errorMessage = String.format(Constants.Category.NOT_EXIST, name);
            logger.error(errorMessage);
            throw new ObjectNotFoundException(errorMessage);
        }
        return optional.get();

    }

    default List<Category> saveAll(List<String> categories){
        List<Category> collect = categories.stream().map(name->{
            Category category = new Category();
            category.setName(name);
            category.setCreateTimeStamp(Instant.now().toString());
            category.setUpdateTimeStamp(Instant.now().toString());
            return category;
        }).collect(Collectors.toList());
        return saveAll(collect);
    }

    @Query("SELECT c FROM Category c")
    public List<Category> getAllCategories();

}
