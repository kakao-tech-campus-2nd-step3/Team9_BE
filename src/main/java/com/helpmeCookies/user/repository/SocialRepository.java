package com.helpmeCookies.user.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpmeCookies.user.entity.ArtistInfo;
import com.helpmeCookies.user.entity.Social;
import com.helpmeCookies.user.entity.User;

@Repository
public interface SocialRepository extends JpaRepository<Social, Long> {
	Boolean existsByFollowerAndFollowing(User follower, ArtistInfo following);
	Optional<Social> findByFollowerAndFollowing(User follower, ArtistInfo following);
}
