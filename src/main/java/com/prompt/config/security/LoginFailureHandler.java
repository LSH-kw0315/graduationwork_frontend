package com.prompt.config.security;

import com.prompt.common.util.MessageSourceUtil;
import com.prompt.common.util.MessageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	private final MessageSourceUtil messageSourceUtil;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		// 로그인 성공 후 리다이렉트할 URL
		String targetUrl = "/login.do";

		MessageUtil.setMessage(request, messageSourceUtil.getMessage("error.login"));

		// 리다이렉트 실행
		response.sendRedirect(targetUrl);
	}
}
