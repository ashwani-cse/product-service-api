package com.app.fakestore.consumer.model;

import lombok.Data;

/**
 * @author Ashwani Kumar
 * Created on 03/01/24.
 */
@Data
public class Product {
    private Long id;
    private String title;
    private Double price;
    private Category category;
    private String description;
    private String imageUrl;
}
