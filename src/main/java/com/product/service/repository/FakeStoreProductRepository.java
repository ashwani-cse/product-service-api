package com.product.service.repository;

import com.product.service.dto.FakeStoreDto;

import java.util.List;

public interface FakeStoreProductRepository {

    FakeStoreDto getSingleProduct(Long id);

    List<FakeStoreDto> getAllProductList();

    FakeStoreDto addProduct(FakeStoreDto dto);

    FakeStoreDto updateProduct(FakeStoreDto dto);

    FakeStoreDto replaceProduct(FakeStoreDto dto);

    FakeStoreDto deleteProduct(Long id);

}
