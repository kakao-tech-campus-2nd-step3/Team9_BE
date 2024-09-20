package com.helpmeCookies.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class BusinessArtist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String businessNumber;

	@Column(nullable = false)
	private String openDate;

	@Column(nullable = false)
	private String headName;

	@OneToOne
	private ArtistInfo artistInfo;
}
