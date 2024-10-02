package com.helpmeCookies.product.util;

import org.springframework.data.domain.Sort;

public class SortUtil {

    public static Sort convertProductSort(ProductSort productSort) {
        return switch (productSort) {
            case LATEST -> Sort.by(Sort.Order.desc("createdDate"));
            default -> Sort.unsorted();
        };
    }

}
