package com.helpmeCookies.product.controller;

import static com.helpmeCookies.product.util.SortUtil.convertProductSort;

import com.helpmeCookies.product.dto.ProductRequest;
import com.helpmeCookies.product.dto.ProductResponse;
import com.helpmeCookies.product.entity.Product;
import com.helpmeCookies.product.service.ProductService;
import com.helpmeCookies.product.service.dto.ProductPage;
import com.helpmeCookies.product.util.ProductSort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.save(productRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductInfo(@PathVariable("productId") Long productId) {
        Product product = productService.find(productId);
        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> editProductInfo(@PathVariable("productId") Long productId,
                                                           @RequestBody ProductRequest productRequest) {
        productService.edit(productId, productRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ProductPage.Paging> getProductsByPage(
        @RequestParam("query") String query,
        @RequestParam(name = "size", required = false, defaultValue = "20") int size,
        @RequestParam("page") int page,
        @RequestParam("sort") ProductSort productSort
    ) {
        var sort = convertProductSort(productSort);
        var pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(productService.getProductsByPage(query, pageable));
    }
}
