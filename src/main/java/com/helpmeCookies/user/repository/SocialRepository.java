package com.helpmeCookies.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpmeCookies.user.entity.Social;

public interface SocialRepository extends JpaRepository<Social, Long> {
}
