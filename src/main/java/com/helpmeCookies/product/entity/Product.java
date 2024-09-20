package com.helpmeCookies.product.entity;

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
import jakarta.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
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
	@CollectionTable(name = "user_hashtags")
	@Enumerated(EnumType.STRING)
	private List<HashTag> hashTag;

	@ManyToOne
	private ArtistInfo artistInfo;
}
