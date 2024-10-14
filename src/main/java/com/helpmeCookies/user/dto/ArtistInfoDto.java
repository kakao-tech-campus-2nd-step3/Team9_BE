package com.helpmeCookies.user.dto;

import com.helpmeCookies.user.entity.ArtistInfo;
import com.helpmeCookies.user.entity.ArtistType;

import lombok.Builder;

@Builder
public record ArtistInfoDto(
	Long id,
	Long userId,
	ArtistType artistType,
	String nickname,
	Long totalFollowers,
	Long totalLikes,
	String about
) {
	public static ArtistInfoDto fromEntity(ArtistInfo artistInfo) {
		return ArtistInfoDto.builder()
			.id(artistInfo.getId())
			.userId(artistInfo.getUserId())
			.artistType(artistInfo.getArtistType())
			.nickname(artistInfo.getNickname())
			.totalFollowers(artistInfo.getTotalFollowers())
			.totalLikes(artistInfo.getTotalLikes())
			.about(artistInfo.getAbout())
			.build();
	}
}
