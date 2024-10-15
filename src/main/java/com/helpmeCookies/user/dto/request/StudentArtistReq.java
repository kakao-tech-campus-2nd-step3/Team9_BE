package com.helpmeCookies.user.dto.request;

import com.helpmeCookies.user.dto.StudentArtistDto;

public record StudentArtistReq(
	String schoolEmail,
	String schoolName,
	String major,
	String about
) {
}
