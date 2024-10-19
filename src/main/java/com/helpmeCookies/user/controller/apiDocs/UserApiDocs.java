package com.helpmeCookies.user.controller.apiDocs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.user.dto.UserTypeDto;
import com.helpmeCookies.user.dto.response.UserCommonInfoRes;
import com.helpmeCookies.user.dto.response.UserDetailsInfoRes;
import com.helpmeCookies.user.dto.response.UserFollowingRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "유저 및 팔로우 기능", description = "유저 및 팔로우 기능과 관련된 API")
public interface UserApiDocs {

	@Operation(summary = "유저 일반 정보 조회", description = "로그인한 유저의 username, imageUrl, hashtag를 조회한다.")
	@GetMapping("/v1/users")
	ResponseEntity<UserCommonInfoRes> getUsers(@AuthenticationPrincipal JwtUser jwtUser);

	@Operation(summary = "유저 상세 정보 조회", description = "로그인한 유저의 상세 정보를 조회한다.")
	@GetMapping("/v1/users/details")
	ResponseEntity<UserDetailsInfoRes> getUserDetails(@AuthenticationPrincipal JwtUser jwtUser);

	@Operation(summary = "유저 타입 조회", description = "로그인한 유저의 타입과 권한을 조회한다.")
	@GetMapping("/v1/users/type")
	public ResponseEntity<UserTypeDto> getUserType(@AuthenticationPrincipal JwtUser jwtUser);

	@Operation(summary = "아티스트 팔로우하기", description = "로그인한 유저가 특정 아티스트를 팔로우한다.")
	@PostMapping("/v1/users/following/{artistId}")
	public ResponseEntity<String> followArtist(
		@AuthenticationPrincipal JwtUser jwtUser, @PathVariable Long artistId
	);

	@Operation(summary = "아티스트 팔로우 취소하기", description = "로그인한 유저가 특정 아티스트를 팔로우 취소한다.")
	@DeleteMapping("/v1/users/following/{artistId}")
	public ResponseEntity<String> unfollowArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long artistId
	);

	@Operation(summary = "팔로잉 목록 조회", description = "로그인한 유저의 팔로우한 아티스트 목록을 조회한다.")
	@GetMapping("/v1/users/following")
	public ResponseEntity<Page<UserFollowingRes>> getFollowingList(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	);
}
