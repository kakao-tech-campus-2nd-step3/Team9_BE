package com.helpmeCookies.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.user.dto.UserDto;
import com.helpmeCookies.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	// TODO: 이후 추가되는 요구사항에 따라 별도의 UserRes로 반환
	// TODO: 구매 판매 내역에 대한 추가 정보 필요. Product 도메인 완성이후 추가
	@GetMapping("/v1/users/{userId}")
	public UserDto getUsers(
		@PathVariable Long userId
	) {
		return userService.getUser(userId);
	}

	//유저 팔로우 목록 조회
	@GetMapping("/v1/users/{userId}/follows")
	public String getFollows() {

		return "ok";
	}
}
