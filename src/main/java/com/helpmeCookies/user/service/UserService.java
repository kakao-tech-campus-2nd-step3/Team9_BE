package com.helpmeCookies.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpmeCookies.global.exception.user.ResourceNotFoundException;
import com.helpmeCookies.user.dto.UserDto;
import com.helpmeCookies.user.dto.UserFollowingDto;
import com.helpmeCookies.user.dto.UserInfoDto;
import com.helpmeCookies.user.dto.UserTypeDto;
import com.helpmeCookies.user.dto.response.UserFollowingRes;
import com.helpmeCookies.user.entity.ArtistInfo;
import com.helpmeCookies.user.entity.Social;
import com.helpmeCookies.user.entity.User;
import com.helpmeCookies.user.entity.UserInfo;
import com.helpmeCookies.user.repository.ArtistInfoRepository;
import com.helpmeCookies.user.repository.SocialRepository;
import com.helpmeCookies.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final ArtistInfoRepository artistInfoRepository;
	private final SocialRepository socialRepository;


	@Transactional
	public UserInfoDto getUserInfo(Long userId) {
		UserInfo userInfo = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException())
			.getUserInfo();

		return UserInfoDto.fromEntity(userInfo);
	}

	@Transactional
	public UserDto updateUserInfo(UserInfoDto userInfoDto, Long userId) {

		User existingUser = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException());

		existingUser.updateUserInfo(userInfoDto.toEntity());

		return UserDto.fromEntity(userRepository.save(existingUser));
	}

	@Transactional
	public UserTypeDto getUserType(Long userId) {
		String usertype = artistInfoRepository.findByUserId(userId)
			.map(artistInfo -> artistInfo.getArtistType().getType()) // 값이 있을 때 처리
			.orElse("User"); // 값이 없을 때 기본값

		//artistType을 확인
		return UserTypeDto.builder()
			.userType(usertype)
			.build();
	}

	@Transactional
	public Page<UserFollowingRes> getFollowingWithPaging(Long userId, Pageable pageable) {

		return userRepository.findFollowingUsers(userId, pageable)
			.map(UserFollowingRes::fromDto);
	}

	@Transactional
	public void followArtist(Long userId, Long artistId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException());

		ArtistInfo artistInfo = artistInfoRepository.findByUserId(artistId)
			.orElseThrow(() -> new ResourceNotFoundException());

		if (socialRepository.existsByFollowerAndFollowing(user, artistInfo)) {
			throw new IllegalArgumentException("이미 팔로우한 아티스트입니다.");
		}

		Social social = Social.builder()
			.follower(user)
			.following(artistInfo)
			.build();

		socialRepository.save(social);
	}

	@Transactional
	public void unfollowArtist(Long userId, Long artistId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException());

		ArtistInfo artistInfo = artistInfoRepository.findByUserId(artistId)
			.orElseThrow(() -> new ResourceNotFoundException());

		Social social = socialRepository.findByFollowerAndFollowing(user, artistInfo)
			.orElseThrow(() -> new ResourceNotFoundException());

		socialRepository.delete(social);
	}
}
