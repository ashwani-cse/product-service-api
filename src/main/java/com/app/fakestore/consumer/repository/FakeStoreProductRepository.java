package com.app.fakestore.consumer.repository;

import com.app.fakestore.consumer.dto.FakeStoreDto;
import com.app.fakestore.consumer.model.Product;

import java.util.List;

public interface FakeStoreProductRepository {

    FakeStoreDto getSingleProduct(Long id);

    List<FakeStoreDto> getAllProductList();

    FakeStoreDto addProduct(FakeStoreDto dto);

    FakeStoreDto updateProduct(FakeStoreDto dto);

    FakeStoreDto replaceProduct(FakeStoreDto dto);

    FakeStoreDto deleteProduct(Long id);

}
