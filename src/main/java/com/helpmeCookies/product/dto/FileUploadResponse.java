package com.helpmeCookies.product.dto;

import com.helpmeCookies.product.entity.ProductImage;

public record FileUploadResponse(
        String photoUrl,
        String uuid
) {
    public ProductImage toEntity(Long productId) {
        return ProductImage.builder()
                .productId(productId)
                .photoUrl(photoUrl)
                .uuid(uuid)
                .build();
    }
}
