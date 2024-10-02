package com.helpmeCookies.product.repository;

import com.helpmeCookies.product.entity.Product;
import com.helpmeCookies.product.repository.dto.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.id AS id, p.name AS name, a.name AS artist, p.price AS price " +
        "FROM Product p JOIN p.artistInfo a " +
        "WHERE p.name LIKE %:query%") // Index 미사용
    Page<ProductSearch> findByName(@Param("query") String query, Pageable pageable);

    @Query(value = "SELECT p.id, p.name, a.name AS artist, p.price " +
        "FROM product p JOIN artist_info a ON p.artist_info_id = a.id " +
        "WHERE MATCH(p.name) AGAINST (:query IN BOOLEAN MODE)",
        countQuery = "SELECT COUNT(*) " +
            "FROM product p JOIN artist_info a ON p.artist_info_id = a.id " +
            "WHERE MATCH(p.name) AGAINST (:query IN BOOLEAN MODE)",
        nativeQuery = true) // Index 사용
    Page<ProductSearch> findByNameWithIdx(@Param("query") String query, Pageable pageable);
}
