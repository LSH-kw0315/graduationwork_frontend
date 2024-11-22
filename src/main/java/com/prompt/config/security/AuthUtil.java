package com.prompt.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
	/**
	 * 핸재 인증한 사용자 정보 조회
	 * @return
	 */
	public static UserInfoContext getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return null; // 사용자가 인증되지 않았습니다.
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof UserInfoContext) {
			return (UserInfoContext) principal; // 인증된 사용자의 UserDetails 반환
		} else {
			return null; // 인증 정보에 UserDetails가 없습니다.
		}
	}
}
