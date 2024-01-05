package com.app.fakestore.consumer.controller;

import com.app.fakestore.consumer.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ashwani Kumar
 * Created on 04/01/24.
 */

@RequestMapping("/categories")
@RestController
public class CategoryController {

    @GetMapping()
    public ResponseEntity<List<Product>> getAllCategories(){
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getAllCategory(){
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
