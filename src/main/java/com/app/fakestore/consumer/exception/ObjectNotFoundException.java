package com.app.fakestore.consumer.exception;

/**
 * @author Ashwani Kumar
 * Created on 13/01/24.
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
