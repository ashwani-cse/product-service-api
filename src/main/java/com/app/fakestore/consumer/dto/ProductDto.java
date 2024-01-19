package com.app.fakestore.consumer.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link com.app.fakestore.consumer.model.Product}
 */
public record ProductDto(@NotNull @NotEmpty @NotBlank(message = "Title is required") String title,
                         @NotNull(message = "Price is required") @Digits(integer = 6, fraction = 2) @PositiveOrZero Double price,
                         String description,
                         @NotNull @Pattern(regexp = "^https?://[a-zA-Z0-9.-]+(?:/[a-zA-Z0-9._-]+)*(?:\\?[a-zA-Z0-9._-]+=[a-zA-Z0-9%&]+)*(?:#[a-zA-Z0-9_-]+)?$")
                         @NotEmpty @NotBlank String imageUrl) {
}