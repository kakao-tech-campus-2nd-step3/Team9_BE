package com.helpmeCookies.user.dto.response;

import com.helpmeCookies.user.dto.ArtistInfoDto;
import com.helpmeCookies.user.dto.BusinessArtistDto;
import com.helpmeCookies.user.dto.StudentArtistDto;

public record ArtistDetailsRes(
	String nickname,
	String description,
	Long totalFollowers,
	Long totalLikes,
	String about

) {
	public static ArtistDetailsRes from(ArtistInfoDto artistInfoDto, BusinessArtistDto businessArtistDto) {
		return new ArtistDetailsRes(
			artistInfoDto.nickname(),
			businessArtistDto.headName(),
			artistInfoDto.totalFollowers(),
			artistInfoDto.totalLikes(),
			artistInfoDto.about()
		);
	}

	public static ArtistDetailsRes from(ArtistInfoDto artistInfoDto, StudentArtistDto studentArtistDto) {
		return new ArtistDetailsRes(
			artistInfoDto.nickname(),
			studentArtistDto.schoolName(),
			artistInfoDto.totalFollowers(),
			artistInfoDto.totalLikes(),
			artistInfoDto.about()
		);
	}
}
