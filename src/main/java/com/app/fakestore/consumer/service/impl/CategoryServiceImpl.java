package com.app.fakestore.consumer.service.impl;

import com.app.fakestore.consumer.constant.Constants;
import com.app.fakestore.consumer.exception.ApiException;
import com.app.fakestore.consumer.exception.BadRequestException;
import com.app.fakestore.consumer.exception.ObjectNotFoundException;
import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.repository.CategoryRepository;
import com.app.fakestore.consumer.service.CategoryService;
import com.app.fakestore.consumer.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ashwani Kumar
 * Created on 14/01/24.
 */

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isEmpty()){
            log.error("Category doesn't exist in database.");
            throw new ObjectNotFoundException(Constants.CATEGORY_NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Optional<Category> optional = categoryRepository.findByName(categoryName);
        if(optional.isEmpty()){
            log.error("Category doesn't exist in database.");
            throw new ObjectNotFoundException(Constants.CATEGORY_NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> addCategories(List<String> categories) {
        List<Category> list;
        try {
            List<Category> collect = categories.stream().map(Category::new).collect(Collectors.toList());
            list = categoryRepository.saveAll(collect);
        } catch (Exception e) {
            log.error("Error occurred while adding category: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
        return list;
    }


    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public Category deleteCategoryById(Long id) {
        return null;
    }

    @Override
    public Category deleteCategoryByName() {
        return null;
    }
}
