package com.helpmeCookies.user.entity;

public enum ArtistType {
	BUSINESS("BusinessArtist"), STUDENT("StudentArtist");

	private final String type;

	ArtistType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
