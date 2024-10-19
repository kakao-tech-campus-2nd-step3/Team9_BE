package com.helpmeCookies.user.controller.apiDocs;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.user.dto.request.BusinessArtistReq;
import com.helpmeCookies.user.dto.request.StudentArtistReq;
import com.helpmeCookies.user.dto.response.ArtistDetailsRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "작가 관련 기능", description = "작가 관련 API")
public interface ArtistApiDocs {

	@Operation(summary = "학생 작가 등록", description = "학생 작가 등록")
	@PostMapping("/v1/artists/students")
	ResponseEntity<String> registerStudents(
		@RequestBody StudentArtistReq artistDetailsReq,
		@AuthenticationPrincipal JwtUser jwtUser
	);

	@Operation(summary = "사업자 작가 등록", description = "사업자 작가 등록")
	@PostMapping("/v1/artists/bussinesses")
	ResponseEntity<String> registerbussinsess(
		@RequestBody BusinessArtistReq businessArtistReq,
		@AuthenticationPrincipal JwtUser jwtUser
	);

	@Operation(summary = "작가 프로필 조회", description = "작가 프로필 조회")
	@GetMapping("/v1/artists/{userId}")
	ArtistDetailsRes getArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long userId
	);

	@Operation(summary = "작가 프로필 조회", description = "자신의 작가 프로필 조회")
	@GetMapping("/v1/artist")
	ArtistDetailsRes getArtist(
		@AuthenticationPrincipal JwtUser jwtUser
	);
}
