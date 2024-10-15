package com.helpmeCookies.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.helpmeCookies.global.jwt.JwtProvider;
import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.user.dto.request.BusinessArtistReq;
import com.helpmeCookies.user.dto.request.StudentArtistReq;
import com.helpmeCookies.user.dto.response.ArtistDetailsRes;
import com.helpmeCookies.user.service.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArtistController {
	private final ArtistService artistService;
	private final JwtProvider jwtProvider;


	// 작가 등록(학생)
	@PostMapping("/v1/artists/students")
	public ResponseEntity<String> registerStudents(
		@RequestBody StudentArtistReq artistDetailsReq,
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		artistService.registerStudentsArtist(artistDetailsReq, jwtUser.getId());
		return ResponseEntity.ok().build();
	}

	// 작가 등록(사업자)
	@PostMapping("/v1/artists/bussinesses")
	public ResponseEntity<String> registerbussinsess(
		@RequestBody BusinessArtistReq businessArtistReq,
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		artistService.registerBusinessArtist(businessArtistReq, jwtUser.getId());
		return ResponseEntity.ok().build();
	}

	// 작가 목록 조회(페이징)
	// TODO: 6주차 회의 이후 추가
	@GetMapping("/v1/artists")
	public String getArtists() {
		return "ok";
	}

	// 작가 프로필 조회
	@GetMapping("/v1/artists/{userId}")
	public ArtistDetailsRes getArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long userId
	) {
		return artistService.getArtistDetails(userId);
	}

	// 자기 자신 작가 프로필 조회
	@GetMapping("/v1/artist")
	public ArtistDetailsRes getArtist(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return artistService.getArtistDetails(jwtUser.getId());
	}
}
