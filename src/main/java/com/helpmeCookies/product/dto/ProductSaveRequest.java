package com.helpmeCookies.product.dto;

import com.helpmeCookies.product.entity.Category;
import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.product.entity.Product;
import com.helpmeCookies.user.entity.ArtistInfo;

import java.util.List;

public record ProductSaveRequest(
        String name,
        String category,
        String size,
        Long price,
        String description,
        String preferredLocation,
        List<HashTag> hashTags,
        Long artistInfo
) {
    public Product toEntity(ArtistInfo artistInfo) {
        return Product.builder()
                .name(name)
                .category(Category.fromString(category))
                .size(size)
                .price(price)
                .description(description)
                .preferredLocation(preferredLocation)
                .hashTags(hashTags)
                .artistInfo(artistInfo)
                .build();
    }
}
