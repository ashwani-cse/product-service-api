package com.product.service.service;

import com.product.service.model.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);

    Category getCategoryByName(String categoryName);

    List<Category> getCategories();

    List<Category> addCategories(List<String> categories);

    Category updateCategory(Category category);

    Category deleteCategoryById(Long id);

    Category deleteCategoryByName();


}
