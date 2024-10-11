package com.helpmeCookies.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String photoUrl;

	private Long productId;

	public ProductImage() {}

	@Builder
	public ProductImage(String photoUrl, Long productId) {
		this.photoUrl = photoUrl;
		this.productId = productId;
	}
}
