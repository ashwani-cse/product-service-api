package com.app.fakestore.consumer.service.impl;

import com.app.fakestore.consumer.dto.FakeStoreDto;
import com.app.fakestore.consumer.exception.ApiException;
import com.app.fakestore.consumer.model.Product;
import com.app.fakestore.consumer.repository.FakeStoreProductRepository;
import com.app.fakestore.consumer.service.ProductService;
import com.app.fakestore.consumer.util.ProductFieldMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        return ProductFieldMapper.createProductFromFakeStoreDto(dto);
    }

    @Override
    public List<Product> getAllProductList() {
        List<FakeStoreDto> fakeStoreDtoList = fakeStoreProductRepository.getAllProductList();
        return fakeStoreDtoList.stream()
                .map(ProductFieldMapper::createProductFromFakeStoreDto)
                .collect(Collectors.toList());
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreDto fakeStoreDto = fakeStoreProductRepository.addProduct(
                ProductFieldMapper.createFakeStoreDtoFromProduct(product));
        return ProductFieldMapper.createProductFromFakeStoreDto(fakeStoreDto);
    }

    @Override
    public Product updateProduct(Long id, Product dto) {
        FakeStoreDto product = fakeStoreProductRepository.getSingleProduct(id);
        if (Objects.isNull(product)) {
            log.error("Product doesn't exist!. Adding new entry in FakeStoreAPi");
        }
        return ProductFieldMapper.createProductFromFakeStoreDto(fakeStoreProductRepository
                .updateProduct(ProductFieldMapper.createFakeStoreDtoFromProduct(dto)));
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreDto fakeStoreDto = fakeStoreProductRepository.getSingleProduct(id);
        if (Objects.isNull(fakeStoreDto)) {
            log.error("Product doesn't exist in FakeStoreApi");
            throw new ApiException("Product doesn't exist", HttpStatus.NOT_FOUND.value());
        }
        ProductFieldMapper.mapProductFieldsToFakeStoreDto(product, fakeStoreDto);
        return ProductFieldMapper.createProductFromFakeStoreDto(fakeStoreProductRepository
                .updateProduct(fakeStoreDto));
    }

    @Override
    public void deleteProduct(Long id) {
        FakeStoreDto fakeStoreDto = fakeStoreProductRepository.getSingleProduct(id);
        if (Objects.isNull(fakeStoreDto)) {
            log.error("Product doesn't exist in FakeStoreApi");
            throw new ApiException("Product doesn't exist", HttpStatus.NOT_FOUND.value());
        }
        ProductFieldMapper.createProductFromFakeStoreDto(
                fakeStoreProductRepository.deleteProduct(id));
    }

}
