package com.product.service.elastic_search.service;

import com.product.service.elastic_search.model.ProductDocument;
import com.product.service.elastic_search.repository.ProductSearchRepository;
import org.springframework.stereotype.Service;

/**
 * @author Ashwani Kumar
 * Created on 10/03/24.
 */

@Service
public class ProductSearchService {

    private final ProductSearchRepository productSearchRepository;

    public ProductSearchService(ProductSearchRepository productSearchRepository) {
        this.productSearchRepository = productSearchRepository;
    }

    public void saveProduct(ProductDocument productDocument) {
        productSearchRepository.save(productDocument);
    }

    public void deleteProduct(Long id) {
        productSearchRepository.deleteById(id);
    }

    public void updateProduct(ProductDocument productDocument) {
        productSearchRepository.save(productDocument);
    }

    public ProductDocument findById(Long id) {
        return productSearchRepository.findById(id).orElse(null);
    }

    public Iterable<ProductDocument> findAll() {
        return productSearchRepository.findAll();
    }

    public void deleteAll() {
        productSearchRepository.deleteAll();
    }


}
