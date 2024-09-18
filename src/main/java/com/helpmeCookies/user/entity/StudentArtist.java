package com.helpmeCookies.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class StudentArtist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String schoolEmail;
	@Column(nullable = false)
	private String schoolName;
	@Column(nullable = false)
	private String major;

	// 매핑
	@OneToOne
	private ArtistInfo artistInfo;
}
