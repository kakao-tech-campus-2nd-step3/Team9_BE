package com.helpmeCookies.product.service;

import com.helpmeCookies.product.dto.ProductSaveRequest;
import com.helpmeCookies.product.entity.Product;
import com.helpmeCookies.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(ProductSaveRequest productSaveRequest) {
        //TODO ArtistInfo 코드 병합시 수정 예정
        Product product = productSaveRequest.toEntity(null);
        productRepository.save(product);
        return product;
    }

    public Product find(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
    }
}
