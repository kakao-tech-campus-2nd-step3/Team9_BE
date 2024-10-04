package com.helpmeCookies.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpmeCookies.global.jwt.JwtProvider;
import com.helpmeCookies.global.jwt.JwtUser;
import com.helpmeCookies.user.dto.request.StudentArtistReq;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistE2Etest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JwtProvider jwtProvider;

	@Test
	public void testRegisterStudents_withValidToken() throws Exception {
		// given
		String token = jwtProvider.createToken(
			JwtUser.builder()
				.id(1L)
				.username("JohnDoe")
				.authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
				.build()
		).getAccessToken();

		StudentArtistReq request = new StudentArtistReq(
			"student@example.com",
			"Example University",
			"Computer Science"
		);

		String requestJson = objectMapper.writeValueAsString(request);

		// when & then
		mockMvc.perform(post("/v1/artists/students")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)
				.header("Authorization", "Bearer " + token))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.schoolEmail").value("student@example.com"))
			.andExpect(jsonPath("$.schoolName").value("Example University"))
			.andExpect(jsonPath("$.major").value("Computer Science"))
			.andExpect(jsonPath("$.artistInfo.totalFollowers").value(0))
			.andExpect(jsonPath("$.artistInfo.totalLikes").value(0))
			.andExpect(jsonPath("$.artistInfo.about").doesNotExist());
	}
}
