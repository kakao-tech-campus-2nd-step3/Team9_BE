package com.helpmeCookies.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpmeCookies.user.entity.ArtistInfo;

public interface ArtistInfoRepository extends JpaRepository<ArtistInfo, Long> {
	Optional<ArtistInfo> findByUserId(Long userId);
	boolean existsByUserId(Long userId);
}
