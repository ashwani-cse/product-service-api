package com.product.service.security;

import com.product.service.constant.Constants.ApiSecurity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Ashwani Kumar
 * Created on 21/01/24.
 */
@Profile("apikey")
@Slf4j
@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Value(ApiSecurity.KEY)
    private String KEY;

    /**
     * This method is used to intercept the request before it reaches the controller method.
     * It is used to check if the request has a valid API key in the header.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader(ApiSecurity.X_API_KEY);
        if (apiKey == null || !apiKey.equals(KEY)) {
            log.info(String.format("[preHandle]: %s", ApiSecurity.INVALID_API_KEY));
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ApiSecurity.INVALID_API_KEY);   // Set HTTP status code to Unauthorized and send an error response
            // Stop further processing of the request
            return false; // If you don't return false, the controller method will execute successfully, but only the error response will be sent
        }
        // Continue with the processing of the request
        return true;
    }


}
