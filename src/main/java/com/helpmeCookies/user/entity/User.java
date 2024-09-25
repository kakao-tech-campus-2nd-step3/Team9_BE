package com.helpmeCookies.user.entity;

import java.util.List;

import com.helpmeCookies.product.entity.HashTag;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users") // 예약어 회피
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String birthdate;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String address;

	// 별도의 테이블 생성. 문자열로 저장
	@ElementCollection(targetClass = HashTag.class)
	@CollectionTable(name = "user_hashtag")
	@Enumerated(EnumType.STRING)
	private List<HashTag> hashTags;
}
