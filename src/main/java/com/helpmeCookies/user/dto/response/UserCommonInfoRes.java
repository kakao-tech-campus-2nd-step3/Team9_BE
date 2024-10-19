package com.helpmeCookies.user.dto.response;

import java.util.List;

import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.user.dto.UserDto;
import com.helpmeCookies.user.dto.UserInfoDto;

public record UserCommonInfoRes(
	String username,
	List<HashTag> hashTags,
	String userImageUrl
) {
	public static UserCommonInfoRes fromDto(UserDto userDto) {
		return new UserCommonInfoRes(
			userDto.userInfo().name(),
			userDto.userInfo().hashTags(),
			userDto.userImageUrl()
		);
	}

}
