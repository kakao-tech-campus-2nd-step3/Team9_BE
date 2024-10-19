package com.helpmeCookies.user.repository.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.helpmeCookies.user.dto.UserFollowingDto;
import com.helpmeCookies.user.entity.QArtistInfo;
import com.helpmeCookies.user.entity.QSocial;
import com.helpmeCookies.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<UserFollowingDto> findFollowingUsers(Long userId, Pageable pageable) {
		QUser user = QUser.user;
		QArtistInfo artistInfo = QArtistInfo.artistInfo;
		QSocial social = QSocial.social;

		JPQLQuery<UserFollowingDto> query = queryFactory
			.select(Projections.constructor(
				UserFollowingDto.class,
				user.id,
				user.userInfo.userImageUrl,
				user.userInfo.nickname,
				artistInfo.totalFollowers,
				artistInfo.totalLikes
			))
			.from(social)
			.join(user).on(user.id.eq(social.following.userId))
			.join(social.following, artistInfo)
			.where(social.follower.id.eq(userId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		List<UserFollowingDto> content = query.fetch();
		long total = query.fetchCount();

		return new PageImpl<>(content, pageable, total);
	}
}
