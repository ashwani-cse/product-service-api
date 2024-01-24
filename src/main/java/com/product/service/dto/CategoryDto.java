package com.product.service.dto;

import com.product.service.constant.Constants;
import com.product.service.model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Category}
 */
public record CategoryDto(@NotNull(message = Constants.Category.REQUIRED)
                          @NotEmpty(message = Constants.Category.CATEGORIES_REQUIRED)
                          List<String> categories) implements Serializable {
}