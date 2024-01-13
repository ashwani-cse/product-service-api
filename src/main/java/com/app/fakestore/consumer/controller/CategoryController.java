package com.app.fakestore.consumer.controller;

import com.app.fakestore.consumer.exception.ApiException;
import com.app.fakestore.consumer.exception.BadRequestException;
import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.model.Product;
import com.app.fakestore.consumer.service.CategoryService;
import com.app.fakestore.consumer.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ashwani Kumar
 * Created on 04/01/24.
 */

@Slf4j
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getCategories();
        if (ApplicationUtil.isEmpty(categories))
            log.error("No category exist in the database");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategory(@PathVariable("name") String name) {
        Category category = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<Category>> addCategories(@RequestBody List<String> categories) {
        if (ApplicationUtil.isEmpty(categories))
            throw new BadRequestException("Add at least one category");
        List<Category> list = categoryService.addCategories(categories);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
