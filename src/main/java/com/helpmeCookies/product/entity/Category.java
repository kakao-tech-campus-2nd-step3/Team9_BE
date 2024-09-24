package com.helpmeCookies.product.entity;

public enum Category {
	PICTURE("사진"),
	ORIENTAL("동양화"),
	WESTERN("서양화"),
	PIECE("조각화"),
	CERAMIC("도예/공예"),
	NEWMEDIA("뉴미디어"),
	DESIGN("디자인"),
	DRAWING("드로잉/판화"),
	CALLIGRAPHY("서예/캘리그라피")
	;

	private String name;

	Category(String name) {
		this.name = name;
	}
}
