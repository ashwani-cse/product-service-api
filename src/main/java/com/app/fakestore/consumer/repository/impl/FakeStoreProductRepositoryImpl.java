package com.app.fakestore.consumer.repository.impl;

import com.app.fakestore.consumer.dto.FakeStoreDto;
import com.app.fakestore.consumer.exception.ApiException;
import com.app.fakestore.consumer.repository.FakeStoreProductRepository;
import com.app.fakestore.consumer.repository.RestClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
            String errorMessage = String.format("No product received for id: %d", id);
            log.error(errorMessage);
            throw new ApiException(errorMessage, HttpStatus.NO_CONTENT.value());
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
            throw new ApiException("No response received", HttpStatus.NO_CONTENT.value());
        }
        return Arrays.stream(productList)
                .collect(Collectors.toList());
    }

    @Override
    public FakeStoreDto addProduct(FakeStoreDto dto) {
        HttpEntity<FakeStoreDto> httpEntity = new HttpEntity<>(dto);
        ResponseEntity<FakeStoreDto> responseEntity= (ResponseEntity<FakeStoreDto>)restClientService.invokeRequest(FAKE_STORE_URL, HttpMethod.POST, httpEntity, FakeStoreDto.class);
        FakeStoreDto fakeStoreDto = responseEntity.getBody();
        if(Objects.isNull(fakeStoreDto)){
            log.error("Product not created in FakeStoreApi Server");
            throw new ApiException("Product not created in FakeStoreApi Server", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return fakeStoreDto;
    }


}
