package com.app.fakestore.consumer.constant;

/**
 * @author Ashwani Kumar
 * Created on 13/01/24.
 */
public final class Constants {


    public interface Product {

        String TITLE_REQUIRED = "Title is required";
        String PRICE_REQUIRED = "Price is required";
        String NOT_EXIST = "Product doesn't exist in database";
    }

    public interface Category {

        String CATEGORIES_REQUIRED = "At least one category must be provided";
        String REQUIRED = "Category is required";
        String NOT_EXIST = "Invalid Category %s";

    }

}
