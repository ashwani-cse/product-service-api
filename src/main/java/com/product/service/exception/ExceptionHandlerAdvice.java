package com.product.service.exception;

import com.product.service.dto.ErrorPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import static com.product.service.exception.ErrorResponse.handlerException;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ErrorPayload> handleBadRequestException(RestClientResponseException exception) {
        return handlerException(exception.getStatusCode().value(), exception.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorPayload> handleApiException(ApiException exception) {
        log.error("handleApiException: {}", exception.getMessage());
        return handlerException(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorPayload> handleObjectNotFoundException(ObjectNotFoundException exception) {
        log.error("handleObjectNotFoundException: {}", exception.getMessage());
        return handlerException(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

}
