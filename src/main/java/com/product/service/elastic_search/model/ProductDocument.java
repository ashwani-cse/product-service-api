package com.product.service.elastic_search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Ashwani Kumar
 * Created on 10/03/24.
 */

@Data
@Document(indexName = "products")
public class ProductDocument {
    @Id
    private Long id;
    private String title;
    private Double price;
    @Field(type = FieldType.Nested)
    private CategoryDocument category;
    private String description;
    private String imageUrl;
    private String productUUID;
    private String createTimeStamp;
    private String updateTimeStamp;

}
