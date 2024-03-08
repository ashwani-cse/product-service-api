package com.product.service.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Ashwani Kumar
 * Created on 20/02/24.
 */

// This class is required because we added security dependency in our project.
// This class is used to configure the security filter chain with Oauth2.0 Resource Server support or basic authentication.
@Configuration
public class SecurityConfig {

    /**
     * This method is used to configure the security filter chain with Oauth2.0 Resource Server support
     * and JWT-encoded bearer tokens. Use this bean only when the jwt token is to be used for endpoint security.
     * Because we are already validating at API-Gateway level, we don't need to validate again at service level again
     * to avoid redundant network calls.  This is business call, if you want to validate at service level as well,
     * Another approach  we can use for this purpose is API-Key passed in header from API-gateway, or mTLS or Service accounts etc.
     */
    @Profile("oauth2")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection, else it will give 403 forbidden
               .oauth2ResourceServer(oauth2ResourceServer -> // Configure OAuth 2.0 Resource Server support
                        oauth2ResourceServer
                                .jwt(Customizer.withDefaults())
                );
        return http.build();
    }

    /*
    *  Permit all endpoints to avoid redundant network calls for jwt token validation.
    * */
    @Profile("basic-auth")
    @Bean
    public SecurityFilterChain securityFilterChain_Unsecure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().permitAll()
                );
        return http.build();
    }

}
