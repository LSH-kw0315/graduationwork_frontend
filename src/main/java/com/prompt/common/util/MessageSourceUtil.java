package com.prompt.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSourceUtil {
	private final MessageSource messageSource;

	public String getMessage(String code) {
		return getMessage(code, (Object)null);
	}

	public String getMessage(String code, Object... args) {
		 return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}
}

