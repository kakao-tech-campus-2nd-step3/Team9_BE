package com.helpmeCookies.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpmeCookies.user.entity.ArtistInfo;

public interface ArtistInfoRepository extends JpaRepository<ArtistInfo, Long> {
}
