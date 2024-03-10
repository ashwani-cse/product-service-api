package com.product.service.exception;

import com.product.service.dto.ErrorPayload;
import com.product.service.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class ApiValidationExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorPayload> handleNoResourceFoundException(NoResourceFoundException exception) {
        log.error("handleNoResourceFoundException: {}", exception.getMessage());
        return ErrorResponse.handlerException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NoSuchMethodError.class)
    public ResponseEntity<ErrorPayload> handleNoSuchMethodError(NoSuchMethodError error) {
        log.error("handleNoSuchMethodError: {}", error.getMessage());
        return ErrorResponse.handlerException(HttpStatus.BAD_REQUEST.value(), "API method doesn't exist");
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorPayload> handleHandlerMethodValidationException(HandlerMethodValidationException exception) {
        log.error("handleHandlerMethodValidationException: {}", exception.getMessage());
        return ErrorResponse.handlerException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorPayload> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("handleHttpMessageNotReadableException: {}", exception.getMessage());
        return ErrorResponse.handlerException(HttpStatus.BAD_REQUEST.value(), ApplicationUtil.extractStringOfFirstOccurance(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorPayload> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("handleMethodArgumentNotValidException: {}", exception.getMessage());
        BindingResult bindingResult = exception.getBindingResult();
        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
        return ErrorResponse.handlerException(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorPayload> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error("handleMethodArgumentTypeMismatchException: {}", exception.getMessage());
        return ErrorResponse.handlerException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorPayload> handleBadRequestException(BadRequestException exception) {
        log.error("handleBadRequestException: {}", exception.getMessage());
        return ErrorResponse.handlerException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
