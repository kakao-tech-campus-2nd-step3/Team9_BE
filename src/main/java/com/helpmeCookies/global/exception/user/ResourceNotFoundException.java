package com.helpmeCookies.global.exception.user;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException() {
		super("Resource not found");
	}
}
