package com.helpmeCookies.user.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.helpmeCookies.user.dto.UserFollowingDto;

public interface UserCustomRepository {
	// User 테이블의 ImageUrl과 ArtistInfo의 totalFollows,totalLikes,nickname,id를 pageable하게 가져오는 쿼리
	Page<UserFollowingDto> findFollowingUsers(Long userId, Pageable pageable);
}
