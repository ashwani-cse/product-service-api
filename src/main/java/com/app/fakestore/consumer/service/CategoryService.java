package com.app.fakestore.consumer.service;

import com.app.fakestore.consumer.model.Category;

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
