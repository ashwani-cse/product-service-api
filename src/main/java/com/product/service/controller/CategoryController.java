package com.product.service.controller;

import static com.product.service.constant.Constants.ApiSecurity.PATH;

import com.product.service.dto.CategoryDto;
import com.product.service.model.Category;
import com.product.service.service.CategoryService;
import com.product.service.util.ApplicationUtil;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ashwani Kumar
 * Created on 04/01/24.
 */

@Slf4j
@RequestMapping(PATH + "/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<CategoryDto> getAllCategories() {
        List<Category> list = categoryService.getCategories();
        if (ApplicationUtil.isEmpty(list))
            log.error("No category exist in the database");
        return new ResponseEntity<>(mapCategories(list), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category.getName(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<String> getCategory(@PathVariable("name") String name) {
        Category category = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category.getName(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> addCategories(@Valid @RequestBody CategoryDto categoryDto) {
        List<Category> list = categoryService.addCategories(categoryDto.categories());
        return new ResponseEntity<>(mapCategories(list), HttpStatus.OK);
    }

    private CategoryDto mapCategories(List<Category> list) {
        return new CategoryDto(list.stream().map(Category::getName).toList());
    }
}
