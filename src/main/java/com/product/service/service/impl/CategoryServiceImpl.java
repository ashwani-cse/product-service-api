package com.product.service.service.impl;

import com.product.service.constant.Constants;
import com.product.service.exception.BadRequestException;
import com.product.service.exception.ObjectNotFoundException;
import com.product.service.model.Category;
import com.product.service.repository.CategoryRepository;
import com.product.service.service.CategoryService;
import com.product.service.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        if (optional.isEmpty()) {
            log.error("Category doesn't exist in database.");
            throw new ObjectNotFoundException(Constants.Category.NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Optional<Category> optional = categoryRepository.findByName(categoryName);
        if (optional.isEmpty()) {
            log.error("Category doesn't exist in database.");
            throw new ObjectNotFoundException(Constants.Category.NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public List<Category> getCategories() {
        //return categoryRepository.findAll();
        return categoryRepository.getAllCategories();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Category> addCategories(List<String> categories) {
        List<Category> list;

        List<Category> existingCategories = categoryRepository.findAll();
        if (ApplicationUtil.isNotEmpty(existingCategories)) {
            Set<String> existCategoryNames = existingCategories.stream().map(Category::getName).collect(Collectors.toSet());
            categories = categories.stream().filter(newCategory -> !existCategoryNames.contains(newCategory)).toList();
        }
        if (ApplicationUtil.isEmpty(categories)) {
            log.error("All {} categories exist in database.", categories.size());
            return new ArrayList<>();
        }
        try {
            return categoryRepository.saveAll(categories);
        } catch (Exception e) {
            log.error("Error occurred while adding category: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
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
