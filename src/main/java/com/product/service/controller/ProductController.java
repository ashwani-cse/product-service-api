package com.product.service.controller;

import static com.product.service.constant.Constants.ApiSecurity.PATH;

import com.product.service.elastic_search.model.CategoryDocument;
import com.product.service.elastic_search.model.ProductDocument;
import com.product.service.elastic_search.service.ProductSearchService;
import com.product.service.model.Category;
import com.product.service.model.Product;
import com.product.service.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ashwani Kumar
 * Created on 03/01/24.
 */

@Slf4j
@RequestMapping(PATH + "/products")
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductSearchService productSearchService;

    public ProductController(ProductService productService, ProductSearchService productSearchService) {
        this.productService = productService;
        this.productSearchService = productSearchService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = productService.getAllProductList();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Product>> getAllProductsByPagination(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String order) {
        List<Product> list = productService.getAllProductList(page, size, sortBy, order);
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        //add to elastic search index
        try {
            productSearchService.saveProduct(convertToProductDocument(addedProduct));
        } catch (Exception e) {
            log.error("Error while adding product to elastic search index: {}", e.getMessage());
        }
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> addProduct(@Valid @RequestBody List<Product> products) {
        products.forEach(productService::addProduct);
        List<Product> list = productService.getAllProductList();
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product = productService.replaceProduct(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product = productService.updateProduct(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Elastic Search APIs for ProductDocument starts here
    @GetMapping("/elastic-search")
    public ResponseEntity<ProductDocument> searchProduct(@RequestParam Long id) {
        ProductDocument product = productSearchService.findById(id);
        log.info("ProductDocument: {}", product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private ProductDocument convertToProductDocument(Product addedProduct) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(addedProduct.getId());
        productDocument.setTitle(addedProduct.getTitle());
        productDocument.setPrice(addedProduct.getPrice());
        Category category = addedProduct.getCategory();
        CategoryDocument categoryDocument = new CategoryDocument();
        categoryDocument.setId(category.getId());
        categoryDocument.setName(category.getName());
        productDocument.setCategory(categoryDocument);
        productDocument.setDescription(addedProduct.getDescription());
        productDocument.setImageUrl(addedProduct.getImageUrl());
        productDocument.setProductUUID(addedProduct.getProductUUID());
        return productDocument;
    }
}
