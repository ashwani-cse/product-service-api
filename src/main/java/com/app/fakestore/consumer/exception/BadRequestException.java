package com.app.fakestore.consumer.exception;

/**
 * @author Ashwani Kumar
 * Created on 14/01/24.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
