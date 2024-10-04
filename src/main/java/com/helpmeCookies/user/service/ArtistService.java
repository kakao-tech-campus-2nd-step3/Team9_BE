package com.helpmeCookies.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpmeCookies.user.dto.ArtistInfoDto;
import com.helpmeCookies.user.dto.BusinessArtistDto;
import com.helpmeCookies.user.dto.StudentArtistDto;
import com.helpmeCookies.user.dto.request.BusinessArtistReq;
import com.helpmeCookies.user.dto.request.StudentArtistReq;
import com.helpmeCookies.user.entity.ArtistInfo;
import com.helpmeCookies.user.entity.BusinessArtist;
import com.helpmeCookies.user.entity.StudentArtist;
import com.helpmeCookies.user.repository.ArtistInfoRepository;
import com.helpmeCookies.user.repository.BusinessArtistRepository;
import com.helpmeCookies.user.repository.StudentArtistRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ArtistService {
	private final BusinessArtistRepository businessArtistRepository;
	private final StudentArtistRepository studentArtistRepository;

	@Transactional
	public StudentArtistDto registerStudentsArtist(StudentArtistReq studentArtistReq, Long userId) {
		// StudentArtist 생성
		ArtistInfo artistInfo = ArtistInfo.builder()
			.totalFollowers(0L)
			.totalLikes(0L)
			.build();

		StudentArtist studentArtist = StudentArtist.builder()
			.userId(userId)
			.schoolEmail(studentArtistReq.schoolEmail())
			.schoolName(studentArtistReq.schoolName())
			.major(studentArtistReq.major())
			.artistInfo(artistInfo)
			.build();

		studentArtist = studentArtistRepository.save(studentArtist);

		return StudentArtistDto.fromEntity(studentArtist);
	}

	@Transactional
	public BusinessArtistDto registerBusinessArtist(BusinessArtistReq businessArtistReq, Long userId) {
		// BusinessArtist 생성
		ArtistInfo artistInfo = ArtistInfo.builder()
			.totalFollowers(0L)
			.totalLikes(0L)
			.build();

		BusinessArtist businessArtist = BusinessArtist
			.builder()
			.userId(userId)
			.businessNumber(businessArtistReq.businessNumber())
			.openDate(businessArtistReq.openDate())
			.headName(businessArtistReq.headName())
			.artistInfo(artistInfo)
			.build();

		businessArtist = businessArtistRepository.save(businessArtist);

		return BusinessArtistDto.fromEntity(businessArtist);
	}

}
