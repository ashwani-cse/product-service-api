package com.product.service.dto;

import lombok.Data;

/**
 * @author Ashwani Kumar
 * Created on 05/01/24.
 */
@Data
public class ErrorPayload {

    private int statusCode;
    private String message;

}
