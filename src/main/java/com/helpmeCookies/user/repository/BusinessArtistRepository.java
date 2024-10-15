package com.helpmeCookies.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpmeCookies.user.entity.ArtistInfo;
import com.helpmeCookies.user.entity.BusinessArtist;

public interface BusinessArtistRepository extends JpaRepository<BusinessArtist, Long> {
	Optional<BusinessArtist> findByArtistInfo(ArtistInfo artistInfo);
}
