package com.helpmeCookies.product.service;

import com.helpmeCookies.product.dto.ProductRequest;
import com.helpmeCookies.product.entity.Category;
import com.helpmeCookies.product.entity.Product;
import com.helpmeCookies.product.repository.ProductImageRepository;
import com.helpmeCookies.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public Product save(ProductRequest productSaveRequest) {
        //TODO ArtistInfo 코드 병합시 수정 예정
        Product product = productSaveRequest.toEntity(null);
        productRepository.save(product);
        return product;
    }

    public Product find(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
    }

    @Transactional
    public void edit(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
        //TODO ArtistInfo 코드 병합시 수정 예정
        product.update(
                productRequest.name(),
                Category.fromString(productRequest.category()),
                productRequest.size(),
                productRequest.price(),
                productRequest.description(),
                productRequest.preferredLocation(),
                productRequest.hashTags(),
                null);
    }

    public void delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
        productRepository.delete(product);
        productImageRepository.deleteAllByProductId(productId);
    }
}
