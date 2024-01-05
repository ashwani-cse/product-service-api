package com.app.fakestore.consumer.exception;

import com.app.fakestore.consumer.dto.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ErrorPayload> handleBadRequestException(RestClientResponseException exception) {
        return handlerException(exception.getStatusCode().value(), exception.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorPayload> handleApiException(ApiException exception) {
        return handlerException(exception.getCode(), exception.getMessage());
    }

    private ResponseEntity<ErrorPayload> handlerException(int statusCode, String errorMessage) {
        ErrorPayload errorPayload = new ErrorPayload();
        errorPayload.setStatusCode(statusCode);
        errorPayload.setMessage(errorMessage);
        return new ResponseEntity<>(errorPayload, HttpStatus.valueOf(statusCode));
    }
}
