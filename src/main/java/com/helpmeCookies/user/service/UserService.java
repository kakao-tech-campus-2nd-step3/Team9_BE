package com.helpmeCookies.user.service;

import org.springframework.stereotype.Service;

import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.user.dto.UserDto;
import com.helpmeCookies.user.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public UserDto getUser(Long userId) {
		return UserDto.fromEntity(userRepository.findById(userId).orElseThrow());
	}
}
