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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "유저 및 팔로우 기능", description = "유저 및 팔로우 기능과 관련된 API")
public class UserController {
	private final UserService userService;


	@Operation(summary = "유저 일반 정보 조회", description = "로그인한 유저의 username, imageUrl, hashtag를 조회한다.")
	@GetMapping("/v1/users")
	public UserCommonInfoRes getUsers(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return UserCommonInfoRes.fromDto(userService.getUserInfo(jwtUser.getId()));
	}

	// 유저 상세 정보 조회
	@Operation(summary = "유저 상세 정보 조회", description = "로그인한 유저의 이름, 주소를 비롯한 개인정보를 함께 조회한다.")
	@GetMapping("/v1/users/details")
	public UserDetailsInfoRes getUserDetails(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return UserDetailsInfoRes.fromDto(userService.getUserInfo(jwtUser.getId()));
	}

	// 유저 타입 조회
	@Operation(summary = "유저 타입 조회", description = "로그인한 유저의 타입과 권한을 조회한다.")
	@GetMapping("/v1/users/type")
	public UserTypeDto getUserType(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return userService.getUserType(jwtUser.getId());
	}

	// 유저 정보 수정
	@Operation(summary = "유저 정보 수정", description = "로그인한 유저의 개인정보를 수정한다.")
	@PutMapping("/v1/users")
	public String updateUserInfo(
		@AuthenticationPrincipal JwtUser jwtUser,
		@RequestBody UserInfoReq userInfoReq
	) {
		// UserInfoDto를 통해서 유저 정보를 수정한다.
		userService.updateUserInfo(userInfoReq.toDto(), jwtUser.getId());
		return "ok";
	}

	@Operation(summary = "아티스트 팔로우하기", description = "로그인한 유저가 특정 아티스트를 팔로우한다.")
	@PostMapping("/v1/users/following/{artistId}")
	public String followArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long artistId
	) {
		userService.followArtist(jwtUser.getId(), artistId);
		return "ok";
	}

	@Operation(summary = "아티스트 팔로우 취소하기", description = "로그인한 유저가 특정 아티스트를 팔로우 취소한다.")
	@DeleteMapping("/v1/users/following/{artistId}")
	public String unfollowArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long artistId
	) {
		userService.unfollowArtist(jwtUser.getId(), artistId);
		return "ok";
	}

	@Operation(summary = "팔로잉 목록 조회", description = "로그인한 유저의 팔로우한 아티스트 목록을 조회한다.")
	@GetMapping("/v1/users/following")
	public Page<UserFollowingRes> getFollowingList(
		@AuthenticationPrincipal JwtUser jwtUser,
			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		return userService.getFollowingWithPaging(jwtUser.getId(),pageable);
	}

	// 유저 탈퇴
	@Operation(summary = "유저 탈퇴", description = "로그인한 유저의 정보를 삭제한다.")
	@DeleteMapping("/v1/users")
	public String deleteUser(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return "ok";
	}
}
