package com.helpmeCookies.user.dto.response;

import com.helpmeCookies.user.dto.ArtistInfoDto;

public record ArtistInfoRes(
	Long totalFollowers,
	Long totalLikes,
	String about
) {
	public static ArtistInfoRes from(ArtistInfoDto artistInfoDto) {
		return new ArtistInfoRes(
			artistInfoDto.totalFollowers(),
			artistInfoDto.totalLikes(),
			artistInfoDto.about());
	}
}
