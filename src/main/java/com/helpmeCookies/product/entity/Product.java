package com.helpmeCookies.product.entity;

import com.helpmeCookies.global.entity.BaseTimeEntity;
import jakarta.persistence.JoinColumn;
import java.util.List;

import com.helpmeCookies.user.entity.ArtistInfo;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
public class Product extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(nullable = false)
	private String size;

	@Column(nullable = false)
	private Long price;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String preferredLocation;

	@ElementCollection(targetClass = HashTag.class)
	@CollectionTable(name = "product_hashtags")
	@Enumerated(EnumType.STRING)
	private List<HashTag> hashTags;

	@ManyToOne
	@JoinColumn(name = "artist_info_id")
	private ArtistInfo artistInfo;

	public Product() {}

	@Builder
	public Product(String name, Category category, String size, Long price, String description, String preferredLocation, List<HashTag> hashTags, ArtistInfo artistInfo) {
		this.name = name;
		this.category = category;
		this.size = size;
		this.price = price;
		this.description = description;
		this.preferredLocation = preferredLocation;
		this.hashTags = hashTags;
		this.artistInfo = artistInfo;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public String getSize() {
		return size;
	}

	public Long getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public String getPreferredLocation() {
		return preferredLocation;
	}

	public List<HashTag> getHashTags() {
		return hashTags;
	}

	public ArtistInfo getArtistInfo() {
		return artistInfo;
	}

	public void update(String name, Category category, String size, Long price, String description, String preferredLocation, List<HashTag> hashTags, ArtistInfo artistInfo) {
		this.name = name;
		this.category = category;
		this.size = size;
		this.price = price;
		this.description = description;
		this.preferredLocation = preferredLocation;
		this.hashTags = hashTags;
		this.artistInfo = artistInfo;
	}
}
