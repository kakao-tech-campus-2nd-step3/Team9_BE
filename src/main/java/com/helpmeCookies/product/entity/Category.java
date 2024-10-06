package com.helpmeCookies.product.entity;

import java.util.HashMap;
import java.util.Map;

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

	private final String name;
	private static final Map<String, Category> nameToCategoryMap = new HashMap<>();

	static {
		for (Category category : Category.values()) {
			nameToCategoryMap.put(category.getName(), category);
		}
	}

	Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Category fromString(String name) {
		System.out.println(name);
		Category category = nameToCategoryMap.get(name);
		if (category == null) {
			throw new IllegalArgumentException(name + "에 해당하는 카테고리가 없습니다.");
		}
		return category;
	}
}
