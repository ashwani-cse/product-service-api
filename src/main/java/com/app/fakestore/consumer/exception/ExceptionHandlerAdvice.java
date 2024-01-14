package com.app.fakestore.consumer.exception;

import com.app.fakestore.consumer.dto.ErrorPayload;
import com.app.fakestore.consumer.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorPayload> handleAnyException(Exception exception){
        log.error("handleAnyException: {}", exception.getMessage());
        return handlerException(HttpStatus.INTERNAL_SERVER_ERROR.value(),  exception.getMessage());
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ErrorPayload> handleBadRequestException(RestClientResponseException exception) {
        return handlerException(exception.getStatusCode().value(), exception.getMessage());
    }
    //HttpMessageNotReadableException

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorPayload> handleBadRequestException(HttpMessageNotReadableException exception) {
        return handlerException(HttpStatus.BAD_REQUEST.value(), ApplicationUtil.extractStringOfFirstOccurance(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorPayload> handleBadRequestException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
        return handlerException(HttpStatus.BAD_REQUEST.value(), message);
    }
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorPayload> handleApiException(ApiException exception) {
        log.error("handleApiException: {}", exception.getMessage());
        return handlerException(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorPayload> handleBadRequestException(BadRequestException exception) {
        log.error("handleBadRequestException: {}", exception.getMessage());
        return handlerException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorPayload> handleObjectNotFoundException(ObjectNotFoundException exception) {
        log.error("handleObjectNotFoundException: {}", exception.getMessage());
        return handlerException(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    private ResponseEntity<ErrorPayload> handlerException(int statusCode, String errorMessage) {
        ErrorPayload errorPayload = new ErrorPayload();
        errorPayload.setStatusCode(statusCode);
        errorPayload.setMessage(errorMessage);
        log.error("handlerException: Error response returned: {}", errorPayload);
        return new ResponseEntity<>(errorPayload, HttpStatus.valueOf(statusCode));
    }
}
