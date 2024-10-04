package com.helpmeCookies.e2e;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.helpmeCookies.global.jwt.JwtProvider;
import com.helpmeCookies.global.jwt.JwtToken;
import com.helpmeCookies.global.jwt.JwtUser;


public class JwtTestUtils {

	public static JwtToken generateValidToken(JwtProvider jwtProvider, Long userId, String username, String role) {
		JwtUser jwtUser = JwtUser.builder()
			.id(userId)
			.username(username)
			.authorities(List.of(new SimpleGrantedAuthority("ROLE_" + role)))
			.build();

		return jwtProvider.createToken(jwtUser);
	}
}
