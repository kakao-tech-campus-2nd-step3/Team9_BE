package com.helpmeCookies.global.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*") // 허용할 도메인 (모든 도메인 허용: "*")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // 허용할 HTTP 메서드
            .allowedHeaders("*") // 허용할 헤더
            .allowCredentials(true); // 인증 정보 허용 여부
    }
}
