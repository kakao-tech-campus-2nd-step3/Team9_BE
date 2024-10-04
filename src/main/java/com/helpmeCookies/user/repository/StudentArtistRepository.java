package com.helpmeCookies.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpmeCookies.user.entity.StudentArtist;

@Repository
public interface StudentArtistRepository extends JpaRepository<StudentArtist, Long> {
}
