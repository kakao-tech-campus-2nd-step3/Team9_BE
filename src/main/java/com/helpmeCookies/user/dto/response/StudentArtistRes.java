package com.helpmeCookies.user.dto.response;

import com.helpmeCookies.user.dto.StudentArtistDto;

public record StudentArtistRes(
	String schoolEmail,
	String schoolName,
	String major,
	ArtistInfoRes artistInfo
) {
	public static StudentArtistRes from(StudentArtistDto studentArtistDto) {
		return new StudentArtistRes(
			studentArtistDto.schoolEmail(),
			studentArtistDto.schoolName(),
			studentArtistDto.major(),
			ArtistInfoRes.from(studentArtistDto.artistInfo())
			);
	}
}
