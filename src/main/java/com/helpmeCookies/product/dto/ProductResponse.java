package com.helpmeCookies.product.dto;

import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.product.entity.Product;

import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String category,
        String size,
        Long price,
        String description,
        String preferredLocation,
        List<HashTag> hashTags,
        ArtistInfo artistInfo
) {
    public static class ArtistInfo {
        private final Long artistId;
        private final String name;

        public ArtistInfo(Long artistId, String name) {
            this.artistId = artistId;
            this.name = name;
        }

        public Long getArtistId() {
            return artistId;
        }

        public String getName() {
            return name;
        }
    }

    public static ProductResponse from(Product product) {
        //TODO artistInfo 코드 개발 이후 수정 예정
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory().getName(),
                product.getSize(),
                product.getPrice(),
                product.getDescription(),
                product.getPreferredLocation(),
                product.getHashTags(),
                new ArtistInfo(
                        1L, "임시"
                )
        );
    }
}
