package com.app.fakestore.consumer.client;

import com.app.fakestore.consumer.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */

@Slf4j
@Component
public class RestClientService {

    private final RestTemplate restTemplate;

    public RestClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> invokeRequest(String endPointUrl, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<?> responseType, Object... uriVariables) {
        try {
            ResponseEntity<?> responseEntity = restTemplate.exchange(endPointUrl, httpMethod, httpEntity, responseType, uriVariables);
            checkSuccessResponse(responseEntity.getStatusCode());
            return responseEntity;
        } catch (RestClientException e) {
            log.error("Error occurred while making api request: {} for endpoint: {}", e.getMessage(), endPointUrl);
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public Object getObject(String endPointUrl, Class<?> responseType) {
        try {
            return restTemplate.getForObject(endPointUrl, responseType);
        } catch (RestClientException e) {
            log.error("Error occurred while making api request: {} for endpoint: {}", e.getMessage(), endPointUrl);
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private void checkSuccessResponse(HttpStatusCode statusCode) {
        if (!statusCode.is2xxSuccessful()) {
            log.error("Unsuccessful response received from Server");
            HttpStatus httpStatus = HttpStatus.valueOf(statusCode.value());
            throw new ApiException(httpStatus.getReasonPhrase(), httpStatus.value());
        }
    }
}
