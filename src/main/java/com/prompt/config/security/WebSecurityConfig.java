package com.prompt.config.security;

import com.prompt.properties.GlobalsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private final LoginSuccessHandler loginSuccessHandler;
	private final GlobalsProperties globalsProperties;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf
				.ignoringRequestMatchers("/common/image/editorUpload.do",
					"/common/fileDeleteAjax.do")


			)
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/loginExpire.do",
					"/join.do",
					"/auth/checkIdAjax.do",
					"/auth/authInsert.do",
					"/webjars/**",
					"/common/css/**",
					"/common/scss/**",
					"/common/js/**",
					"/common/images/**",
					"/common/fonts/**",
					"/common/plugin/**",
					"/web_upload/**",
					"/favicon.ico"
				)
				.permitAll()
				.anyRequest().authenticated()
			)
			// 로그인
			.formLogin(formLogin -> formLogin
				.loginPage("/login.do")
				.loginProcessingUrl("/login.do")
				.usernameParameter("usrId")
				.passwordParameter("paswrd")
				.defaultSuccessUrl("/loginSuccess.do", true)
				.failureUrl("/loginFailure.do")
				.permitAll())
			// 로그아웃 기능 활성화
			.logout(logout -> logout
				.logoutUrl("/logout.do") // 로그아웃 처리 경로
				.logoutSuccessUrl("/logoutCompleted.do") // 로그아웃 성공 후 리다이렉트될 URL
				.permitAll() // 로그아웃 요청에 대해 인증 없이 접근 허용
			);

		// 중복 로그인 설정
		if(!globalsProperties.getDupLoginYn()) {
			http
				.sessionManagement(session -> session
				.sessionFixation()
				.changeSessionId()
				.maximumSessions(1)
				.expiredUrl("/loginExpire.do")
				.maxSessionsPreventsLogin(false)
				.sessionRegistry(sessionRegistry())
			);
		}

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}
}

