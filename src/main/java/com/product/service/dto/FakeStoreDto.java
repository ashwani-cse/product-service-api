package com.product.service.dto;

import lombok.Data;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */
@Data
public class FakeStoreDto {

    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

}
