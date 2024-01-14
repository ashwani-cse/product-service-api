package com.app.fakestore.consumer.repository;

import com.app.fakestore.consumer.constant.Constants;
import com.app.fakestore.consumer.exception.ObjectNotFoundException;
import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


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

}
