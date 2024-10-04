package com.helpmeCookies.user.dto;

import com.helpmeCookies.user.entity.StudentArtist;

public record StudentArtistDto(
	Long id,
	Long userId,
	String schoolEmail,
	String schoolName,
	String major,
	ArtistInfoDto artistInfo
) {
	public static StudentArtistDto fromEntity(StudentArtist studentArtist) {
		return new StudentArtistDto(
			studentArtist.getId(),
			studentArtist.getUserId(),
			studentArtist.getSchoolEmail(),
			studentArtist.getSchoolName(),
			studentArtist.getMajor(),
			ArtistInfoDto.of(
				studentArtist.getArtistInfo().getTotalFollowers(),
				studentArtist.getArtistInfo().getTotalLikes(),
				studentArtist.getArtistInfo().getAbout()
			)
		);
	}
}
