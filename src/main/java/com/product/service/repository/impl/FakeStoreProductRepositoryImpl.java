package com.product.service.repository.impl;

import com.product.service.dto.FakeStoreDto;
import com.product.service.exception.ApiException;
import com.product.service.repository.FakeStoreProductRepository;
import com.product.service.client.RestClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */

@Slf4j
@Repository
public class FakeStoreProductRepositoryImpl implements FakeStoreProductRepository {

    private final RestClientService restClientService;

    private static final String FAKE_STORE_URL = "https://fakestoreapi.com/products/";

    public FakeStoreProductRepositoryImpl(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    @Override
    public FakeStoreDto getSingleProduct(Long id) {
        FakeStoreDto fakeStoreDto = (FakeStoreDto) restClientService.getObject(FAKE_STORE_URL + id, FakeStoreDto.class);
        if (Objects.isNull(fakeStoreDto)) {
            String errorMessage = String.format("Product not found for id: %d", id);
            log.error(errorMessage);
            throw new ApiException(errorMessage, HttpStatus.NOT_FOUND.value());
        }
        return fakeStoreDto;
    }

    @Override
    public List<FakeStoreDto> getAllProductList() {
        ResponseEntity<FakeStoreDto[]> responseEntity = (ResponseEntity<FakeStoreDto[]>)
                restClientService.invokeRequest(FAKE_STORE_URL, HttpMethod.GET, null, FakeStoreDto[].class);
        FakeStoreDto[] productList = responseEntity.getBody();
        if (Objects.isNull(productList)) {
            log.error("No response received from FakeStoreApi while getting all products.");
            throw new ApiException("No response received", HttpStatus.NOT_FOUND.value());
        }
        return Arrays.stream(productList)
                .collect(Collectors.toList());
    }

    @Override
    public FakeStoreDto addProduct(FakeStoreDto dto) {
        FakeStoreDto fakeStoreDto = processProductHttpRequest(dto, HttpMethod.POST);
        if (Objects.isNull(fakeStoreDto)) {
            log.error("Product not created in FakeStoreApi Server");
            throw new ApiException("Product not created in FakeStoreApi Server", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return fakeStoreDto;
    }

    @Override
    public FakeStoreDto updateProduct(FakeStoreDto dto) {
        FakeStoreDto fakeStoreDto = processProductHttpRequest(dto, HttpMethod.PUT);
        if (Objects.isNull(fakeStoreDto)) {
            log.error("Product is not updated in FakeStoreApi Server");
            throw new ApiException("Product is not update in FakeStoreApi Server", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return fakeStoreDto;
    }

    @Override
    public FakeStoreDto replaceProduct(FakeStoreDto dto) {
        FakeStoreDto fakeStoreDto = processProductHttpRequest(dto, HttpMethod.PATCH);
        if (Objects.isNull(fakeStoreDto)) {
            log.error("Product details are not updated in FakeStoreApi Server");
            throw new ApiException("Product details are not updated in FakeStoreApi Server", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return fakeStoreDto;
    }

    @Override
    public FakeStoreDto deleteProduct(Long id) {
        FakeStoreDto fakeStoreDto = processProductHttpRequest(null, HttpMethod.DELETE, id);
        if (Objects.isNull(fakeStoreDto)) {
            log.error("Product is not deleted in FakeStoreApi Server successfully");
            throw new ApiException("Product is not deleted in FakeStoreApi Server successfully", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return fakeStoreDto;
    }

    private FakeStoreDto processProductHttpRequest(FakeStoreDto dto, HttpMethod httpMethod, Object... uriVariables) {
        HttpEntity<FakeStoreDto> httpEntity = new HttpEntity<>(dto);
        ResponseEntity<FakeStoreDto> responseEntity = (ResponseEntity<FakeStoreDto>) restClientService.invokeRequest(FAKE_STORE_URL, httpMethod, httpEntity, FakeStoreDto.class, uriVariables);
        return responseEntity.getBody();
    }

}
