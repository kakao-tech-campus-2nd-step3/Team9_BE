package com.helpmeCookies.user.dto;

import com.helpmeCookies.user.entity.BusinessArtist;

public record BusinessArtistDto(
	Long id,
	String businessNumber,
	String openDate,
	String headName
) {
	public static BusinessArtistDto from(BusinessArtist businessArtist) {
		return new BusinessArtistDto(
			businessArtist.getId(),
			businessArtist.getBusinessNumber(),
			businessArtist.getOpenDate(),
			businessArtist.getHeadName()
		);
	}
}
