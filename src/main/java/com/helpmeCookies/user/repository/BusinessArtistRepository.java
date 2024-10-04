package com.helpmeCookies.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpmeCookies.user.entity.BusinessArtist;

public interface BusinessArtistRepository extends JpaRepository<BusinessArtist, Long> {
}
