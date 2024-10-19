package com.helpmeCookies.user.dto.request;

import java.util.List;

import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.user.dto.UserInfoDto;

public record UserInfoReq(
	String name,
	String userImageUrl,
	String nickname,
	String email,
	String birthdate,
	String phone,
	String address,
	List<HashTag> hashTags
) {
	public UserInfoDto toDto() {
		return new UserInfoDto(
			name,
			userImageUrl,
			nickname,
			email,
			birthdate,
			hashTags
		);
	}
}
