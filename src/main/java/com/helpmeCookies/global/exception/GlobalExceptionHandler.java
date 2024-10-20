package com.helpmeCookies.global.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpmeCookies.global.exception.user.ResourceNotFoundException;
import com.sun.jdi.request.DuplicateRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourceNotFoundException() {
		return "해당 리소스를 찾을 수 없습니다.";
	}

	@ExceptionHandler(DuplicateRequestException.class)
	public String handleDuplicateRequestException() {
		return "이미 생성되었거나 중복된 요청입니다.";
	}
}
