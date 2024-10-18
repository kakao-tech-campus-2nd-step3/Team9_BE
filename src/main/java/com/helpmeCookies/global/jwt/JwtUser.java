package com.helpmeCookies.global.jwt;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtUser implements UserDetails {
	private final Long id;
	private final String username;
	private Collection<? extends GrantedAuthority> authorities;

	public static JwtUser of(Long id) {
		return JwtUser.builder()
			.id(id)
			.build();
	}

	@Override
	// 임시 기본 권한을 USER로 설정
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + "USER"));
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}