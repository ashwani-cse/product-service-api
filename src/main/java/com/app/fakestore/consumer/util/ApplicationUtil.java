package com.app.fakestore.consumer.util;

import java.util.Collection;

/**
 * @author Ashwani Kumar
 * Created on 14/01/24.
 */
public final class ApplicationUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isBlank(String str){
        return str==null || str.isEmpty() ||  str.isBlank();
    }

}