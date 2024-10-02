package com.helpmeCookies.product.service.dto;

import com.helpmeCookies.product.repository.dto.ProductSearch;
import java.util.List;
import org.springframework.data.domain.Page;

public class ProductPage {

    public record Info(
            Long id,
            String name,
            String artist,
            Long price
    ) {
        public static Info from(ProductSearch productSearch) {
            return new Info(
                    productSearch.getId(),
                    productSearch.getName(),
                    productSearch.getArtist(),
                    productSearch.getPrice()
            );
        }

        public static List<Info> of(List<ProductSearch> content) {
            return content.stream()
                    .map(Info::from)
                    .toList();
        }
    }

    public record Paging (
            boolean hasNext,
            List<Info> products
    ) {

        public static Paging from(Page<ProductSearch> productPage) {
            return new Paging(
                    productPage.hasNext(),
                    Info.of(productPage.getContent())
            );
        }
    }

}
