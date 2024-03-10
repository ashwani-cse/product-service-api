package com.product.service.elastic_search.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Ashwani Kumar
 * Created on 10/03/24.
 */

@Data
@Document(indexName = "categories")
public class CategoryDocument{
    private Long id;
    private String name;
    private String createTimeStamp;
    private String updateTimeStamp;

}
