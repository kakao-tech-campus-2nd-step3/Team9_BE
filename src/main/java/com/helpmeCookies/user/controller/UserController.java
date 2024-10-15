package com.helpmeCookies.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.user.dto.UserFollowingDto;
import com.helpmeCookies.user.dto.response.UserCommonInfoRes;
import com.helpmeCookies.user.dto.request.UserInfoReq;
import com.helpmeCookies.user.dto.response.UserDetailsInfoRes;
import com.helpmeCookies.user.dto.UserTypeDto;
import com.helpmeCookies.user.dto.response.UserFollowingRes;
import com.helpmeCookies.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;


	// 유저 일반 정보 조회
	@GetMapping("/v1/users")
	public UserCommonInfoRes getUsers(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return UserCommonInfoRes.fromDto(userService.getUserInfo(jwtUser.getId()));
	}

	// 유저 상세 정보 조회
	@GetMapping("/v1/users/details")
	public UserDetailsInfoRes getUserDetails(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return UserDetailsInfoRes.fromDto(userService.getUserInfo(jwtUser.getId()));
	}

	// 유저 타입 조회
	@GetMapping("/v1/users/type")
	public UserTypeDto getUserType(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return userService.getUserType(jwtUser.getId());
	}

	// 유저 정보 수정
	@PutMapping("/v1/users")
	public String updateUserInfo(
		@AuthenticationPrincipal JwtUser jwtUser,
		@RequestBody UserInfoReq userInfoReq
	) {
		// UserInfoDto를 통해서 유저 정보를 수정한다.
		userService.updateUserInfo(userInfoReq.toDto(), jwtUser.getId());
		return "ok";
	}

	// 아티스트 팔로우하기
	@PostMapping("/v1/users/following/{artistId}")
	public String followArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long artistId
	) {
		userService.followArtist(jwtUser.getId(), artistId);
		return "ok";
	}

	// 아티스트 팔로우 취소하기
	@DeleteMapping("/v1/users/following/{artistId}")
	public String unfollowArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long artistId
	) {
		userService.unfollowArtist(jwtUser.getId(), artistId);
		return "ok";
	}

	// 유저 팔로우 목록 조회
	@GetMapping("/v1/users/following")
	public Page<UserFollowingRes> getFollowingList(
		@AuthenticationPrincipal JwtUser jwtUser,
			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		return userService.getFollowingWithPaging(jwtUser.getId(),pageable);
	}

	// 유저 탈퇴
	@DeleteMapping("/v1/users")
	public String deleteUser(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return "ok";
	}
}
