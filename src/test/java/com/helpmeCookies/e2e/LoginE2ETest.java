package com.helpmeCookies.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.helpmeCookies.e2e.JwtTestUtils;
import com.helpmeCookies.global.jwt.JwtProvider;
import com.helpmeCookies.global.jwt.JwtToken;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // 테스트용 프로파일 사용
public class LoginE2ETest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JwtProvider jwtProvider;

	@Test
	public void testRegisterStudents_withValidToken() throws Exception {
		// given
		JwtToken token = JwtTestUtils.generateValidToken(jwtProvider, 1L, "JohnDoe", "ROLE_USER");

		// when & then
		mockMvc.perform(get("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token.getAccessToken()))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/plain;charset=UTF-8"))
			.andExpect(content().string("johndoe@example.com"));
	}
}
