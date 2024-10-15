package com.helpmeCookies.user.dto.response;

import com.helpmeCookies.user.dto.UserFollowingDto;

public record UserFollowingRes(
	Long userId,
	String nickname,
	String userImageUrl,
	Long totalFollowers,
	Long totalLikes
) {
	public static UserFollowingRes fromDto(UserFollowingDto dto) {
		return new UserFollowingRes(
			dto.userId(),
			dto.nickname(),
			dto.imageUrl(),
			dto.totalFollowers(),
			dto.totalLikes());
	}
}
