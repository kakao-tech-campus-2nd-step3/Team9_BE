package com.helpmeCookies.user.entity;

import java.util.List;

import com.helpmeCookies.product.entity.HashTag;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class UserInfo {

	private String name;

	private String email;

	private String birthdate;

	private String phone;

	private String address;

	@ElementCollection(targetClass = HashTag.class)
	@CollectionTable(name = "user_hashtags")
	@Enumerated(EnumType.STRING)
	private List<HashTag> hashTags; // 기본 FetchType.LAZY
}
