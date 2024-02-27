package com.product.service.config;

import com.product.service.security.HttpRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for defining and configuring custom interceptors in the Spring MVC application.
 * Custom interceptors can be used to perform pre-processing and post-processing of HTTP requests and responses.
 * <p/><b>Note: </b> Interceptors are not ideally suited as a security layer due to the potential for a mismatch with
 * annotated controller path matching, which can also match trailing slashes and path extensions transparently,
 * along with other path matching options. Many of these options have been deprecated, but the potential for a
 * mismatch remains.
 * <p/>
 * Generally, it is recommended to use Spring Security, which includes a dedicated MvcRequestMatcher to align
 * with Spring MVC path matching and also has a security firewall that blocks many unwanted characters in URL paths.
 *
 * @see HttpRequestInterceptor Custom interceptor for handling HTTP requests.
 * @author Ashwani Kumar
 * Created on 21/01/24.
 */

@Profile("apikey")
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // Custom interceptor for handling HTTP requests.
    private final HttpRequestInterceptor httpRequestInterceptor;

    public InterceptorConfig(HttpRequestInterceptor httpRequestInterceptor) {
        this.httpRequestInterceptor = httpRequestInterceptor;
    }

    /**
     * Overrides the addInterceptors method from WebMvcConfigurer to register custom interceptors.
     *
     * @param registry InterceptorRegistry used to register custom interceptors.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(httpRequestInterceptor)
                .addPathPatterns("/api/v1/**");
               // .excludePathPatterns("/admin/**");
    }
}
