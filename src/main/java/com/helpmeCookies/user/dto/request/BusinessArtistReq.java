package com.helpmeCookies.user.dto.request;

import com.helpmeCookies.user.dto.ArtistInfoDto;

public record BusinessArtistReq(
	String businessNumber,
	String openDate,
	String headName
) {

}
