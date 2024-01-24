package com.product.service.model;

import com.product.service.constant.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ashwani Kumar
 * Created on 03/01/24.
 */

@Setter
@Getter
@Entity
public class Product extends  Base{
    @NotBlank(message = Constants.Product.TITLE_REQUIRED)
    private String title;
    @NotNull(message = Constants.Product.PRICE_REQUIRED)
    private Double price;
    @Valid
    @ManyToOne
    private Category category;
    private String description;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Column(name = "product_uuid")
    private String productUUID;
}
