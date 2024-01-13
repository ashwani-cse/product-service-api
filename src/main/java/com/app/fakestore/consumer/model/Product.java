package com.app.fakestore.consumer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @Column(name = "TITLE")
    private String title;
    @Column(name = "PRICE")
    private Double price;
    @ManyToOne
    private Category category;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Column(name = "UUID")
    private String productUUID;
}
