package com.product.service.elastic_search.repository;

import com.product.service.elastic_search.model.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ashwani Kumar
 * Created on 10/03/24.
 */

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, Long> {
}
