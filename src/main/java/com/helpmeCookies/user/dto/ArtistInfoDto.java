package com.helpmeCookies.user.dto;

public record ArtistInfoDto(
	Long totalFollowers,
	Long totalLikes,
	String about
) {
	public static ArtistInfoDto of(Long totalFollowers, Long totalLikes, String about) {
		return new ArtistInfoDto(totalFollowers, totalLikes, about);
	}
}
