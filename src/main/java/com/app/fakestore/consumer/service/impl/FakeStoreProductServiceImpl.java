package com.app.fakestore.consumer.service.impl;

import com.app.fakestore.consumer.dto.FakeStoreDto;
import com.app.fakestore.consumer.model.Product;
import com.app.fakestore.consumer.repository.FakeStoreProductRepository;
import com.app.fakestore.consumer.service.ProductService;
import com.app.fakestore.consumer.util.ProductDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ashwani Kumar
 * Created on 04/01/24.
 */

@Slf4j
@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private final FakeStoreProductRepository fakeStoreProductRepository;

    public FakeStoreProductServiceImpl(FakeStoreProductRepository fakeStoreProductRepository) {
        this.fakeStoreProductRepository = fakeStoreProductRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreDto dto = fakeStoreProductRepository.getSingleProduct(id);
        return ProductDtoMapper.createProductFromFakeStoreDto(dto);
    }

    @Override
    public List<Product> getAllProductList() {
        List<FakeStoreDto> fakeStoreDtoList = fakeStoreProductRepository.getAllProductList();
        return fakeStoreDtoList.stream().map(ProductDtoMapper::createProductFromFakeStoreDto).collect(Collectors.toList());
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreDto fakeStoreDto = fakeStoreProductRepository.addProduct(ProductDtoMapper.createFakeStoreDtoFromProduct(product));
        return ProductDtoMapper.createProductFromFakeStoreDto(fakeStoreDto);
    }

}
