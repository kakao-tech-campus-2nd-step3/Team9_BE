package com.helpmeCookies.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpmeCookies.global.jwt.JwtProvider;
import com.helpmeCookies.global.jwt.JwtToken;
import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.product.entity.HashTag;
import com.helpmeCookies.user.entity.User;
import com.helpmeCookies.user.entity.UserInfo;
import com.helpmeCookies.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//Todo: Swagger 추가
public class LoginController {
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	// 임시 회원가입 url. 유저를 생성하고 jwt 토큰을 반환한다.
	@GetMapping("/test/signup")
	public JwtToken signup() {
		UserInfo userInfo = UserInfo.builder()
			.userImageUrl("https://www.naver.com")
			.email("test@test")
			.birthdate("1995-01-01")
			.phone("010-1234-5678")
			.hashTags(List.of(HashTag.DREAMLIKE))
			.name("test")
			.nickname("test")
			.address("서울시 강남구")
			.build();

		User user = User.builder()
			.userInfo(userInfo)
			.build();

		userRepository.save(user);
		return jwtProvider.createToken(JwtUser.of(user.getId()));
	}

	// 임시 로그인 url. 로그인한 유저의 정보의 일부를 반환한다.
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal JwtUser jwtUser) {
		User user = userRepository.findById(jwtUser.getId()).orElseThrow();
		return user.getUserInfo().getEmail();
	}
}
