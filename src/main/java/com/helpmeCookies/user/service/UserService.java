package com.helpmeCookies.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpmeCookies.global.exception.user.ResourceNotFoundException;
import com.helpmeCookies.user.dto.UserDto;
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
import com.sun.jdi.request.DuplicateRequestException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final ArtistInfoRepository artistInfoRepository;
	private final SocialRepository socialRepository;


	@Transactional
	public UserDto getUserInfo(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 유저입니다."));

		return UserDto.fromEntity(user);
	}

	@Transactional
	public UserDto updateUserInfo(UserInfoDto userInfoDto, Long userId) {

		User existingUser = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 유저입니다."));

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
			.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 유저입니다."));

		ArtistInfo artistInfo = artistInfoRepository.findByUserId(artistId)
			.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 아티스트입니다."));

		if (socialRepository.existsByFollowerAndFollowing(user, artistInfo)) {
			throw new DuplicateRequestException("이미 팔로우한 아티스트입니다.");
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
			.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 유저입니다."));

		ArtistInfo artistInfo = artistInfoRepository.findByUserId(artistId)
			.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 아티스트입니다."));

		Social social = socialRepository.findByFollowerAndFollowing(user, artistInfo)
			.orElseThrow(() -> new ResourceNotFoundException("팔로우하지 않은 아티스트입니다."));

		socialRepository.delete(social);
	}
}
