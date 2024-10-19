package com.helpmeCookies.user.dto.response;

import java.util.List;

import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.user.dto.UserDto;
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
	public static UserDetailsInfoRes fromDto(UserDto userDto) {
		return UserDetailsInfoRes.builder()
			.name(userDto.userInfo().name())
			.userImageUrl(userDto.userImageUrl())
			.nickname(userDto.nickname())
			.email(userDto.userInfo().email())
			.birthdate(userDto.userInfo().birthdate())
			.phone(userDto.userInfo().phone())
			.address(userDto.userInfo().address())
			.hashTags(userDto.userInfo().hashTags())
			.build();
	}
}
