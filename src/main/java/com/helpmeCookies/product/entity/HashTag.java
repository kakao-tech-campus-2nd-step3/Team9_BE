package com.helpmeCookies.product.entity;

public enum HashTag {
	SERENITY("고요함"),
	MELANCHOLY("멜랑꼴리"),
	VIBRANCE("활기"),
	NOSTALGIA("향수"),
	MYSTERY("신비로움"),
	JOYFUL("기쁨"),
	LONELINESS("고독"),
	CONTEMPLATION("사색"),
	WONDER("경이로움"),
	DREAMLIKE("몽환적");

	private String name;

	HashTag(String name) {
		this.name = name;
	}
}
