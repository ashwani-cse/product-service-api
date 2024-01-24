package com.product.service.util;

import com.product.service.dto.FakeStoreDto;
import com.product.service.model.Category;
import com.product.service.model.Product;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */
public class ProductFieldMapper {

    public static void mapProduct(Product source, Product target) {
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getPrice() != null) {
            target.setPrice(source.getPrice());
        }
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
        if (source.getCategory() != null ) {
            target.setCategory(source.getCategory());
        }
        if (source.getImageUrl() != null) {
            target.setImageUrl(source.getImageUrl());
        }
    }

    public static Product createProductFromFakeStoreDto(FakeStoreDto fakeStoreDto) {
        Product product = new Product();
        product.setId(fakeStoreDto.getId());
        Category category = new Category();
        category.setName(fakeStoreDto.getCategory());
        product.setCategory(category);
        product.setDescription(fakeStoreDto.getDescription());
        product.setTitle(fakeStoreDto.getTitle());
        product.setPrice(fakeStoreDto.getPrice());
        product.setImageUrl(fakeStoreDto.getImage());
        return product;
    }

    public static FakeStoreDto createFakeStoreDtoFromProduct(Product product) {
        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        mapProductFieldsToFakeStoreDto(product, fakeStoreDto);
        return fakeStoreDto;
    }

    public static void mapProductFieldsToFakeStoreDto(Product product, FakeStoreDto fakeStoreDto) {
        if (product.getTitle() != null) {
            fakeStoreDto.setTitle(product.getTitle());
        }
        if (product.getPrice() != null) {
            fakeStoreDto.setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
            fakeStoreDto.setDescription(product.getDescription());
        }
        if (product.getCategory() != null && product.getCategory().getName() != null) {
            fakeStoreDto.setCategory(product.getCategory().getName());
        }
        if (product.getImageUrl() != null) {
            fakeStoreDto.setImage(product.getImageUrl());
        }
    }
}
