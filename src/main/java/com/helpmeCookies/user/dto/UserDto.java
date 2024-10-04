package com.helpmeCookies.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.user.entity.User;

public record UserDto(
	Long id,
	String nickname,
	String email,
	String birthdate,
	String phone,
	String address,
	LocalDateTime createdAt,
	List<HashTag> hashTags
) {
	public static UserDto fromEntity(User user) {
		return new UserDto(
			user.getId(),
			user.getNickname(),
			user.getEmail(),
			user.getBirthdate(),
			user.getPhone(),
			user.getAddress(),
			user.getCreatedAt(),
			user.getHashTags()
		);
	}
}
