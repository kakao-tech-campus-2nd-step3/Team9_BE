package com.helpmeCookies.product.controller;

import com.helpmeCookies.product.dto.ProductSaveRequest;
import com.helpmeCookies.product.entity.Product;
import com.helpmeCookies.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody ProductSaveRequest productSaveRequest) {
        Product product = productService.save(productSaveRequest);
        return ResponseEntity.ok().body(product);
    }
}
