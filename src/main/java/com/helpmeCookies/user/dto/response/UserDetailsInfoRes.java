package com.helpmeCookies.user.dto.response;

import java.util.List;

import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.user.dto.UserInfoDto;

import lombok.Builder;

@Builder
public record UserDetailsInfoRes(
	String name,
	String userImageUrl,
	String nickname,
	String email,
	String birthdate,
	String phone,
	String address,
	List<HashTag> hashTags
) {
	public static UserDetailsInfoRes fromDto(UserInfoDto userInfoDto) {
		return UserDetailsInfoRes.builder()
			.name(userInfoDto.name())
			.userImageUrl(userInfoDto.userImageUrl())
			.nickname(userInfoDto.nickname())
			.email(userInfoDto.email())
			.birthdate(userInfoDto.birthdate())
			.phone(userInfoDto.phone())
			.address(userInfoDto.address())
			.hashTags(userInfoDto.hashTags())
			.build();
	}
}
