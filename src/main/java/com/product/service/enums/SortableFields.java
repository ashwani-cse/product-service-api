package com.product.service.enums;

/**
 * @author Ashwani Kumar
 * Created on 09/03/24.
 */
public enum SortableFields {
    ID,
    NAME,
    PRICE,
    CATEGORY,
    CREATED_AT,
    UPDATED_AT;

    public static SortableFields[] getSortableFields() {
        return SortableFields.values();
    }

}
