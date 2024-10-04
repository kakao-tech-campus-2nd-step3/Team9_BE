package com.helpmeCookies.user.dto.response;

import com.helpmeCookies.user.dto.BusinessArtistDto;

public record BusinessArtistRes(
	String businessNumber,
	String openDate,
	String headName,
	ArtistInfoRes artistInfo
) {
	public static BusinessArtistRes from(BusinessArtistDto businessArtistDto) {
		return new BusinessArtistRes(
			businessArtistDto.businessNumber(),
			businessArtistDto.openDate(),
			businessArtistDto.headName(),
			ArtistInfoRes.from(businessArtistDto.artistInfo())
		);
	}
}
