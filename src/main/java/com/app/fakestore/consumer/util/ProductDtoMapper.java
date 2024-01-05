package com.app.fakestore.consumer.util;

import com.app.fakestore.consumer.dto.FakeStoreDto;
import com.app.fakestore.consumer.model.Category;
import com.app.fakestore.consumer.model.Product;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */
public class ProductDtoMapper {

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
        fakeStoreDto.setId(product.getId());
        fakeStoreDto.setCategory(product.getCategory().getName());
        fakeStoreDto.setDescription(product.getDescription());
        fakeStoreDto.setTitle(product.getTitle());
        fakeStoreDto.setPrice(product.getPrice());
        fakeStoreDto.setImage(product.getImageUrl());
        return fakeStoreDto;
    }
}
