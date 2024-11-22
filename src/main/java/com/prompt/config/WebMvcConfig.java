package com.prompt.config;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Value("${globals.file-webroot-path}")
	private String webrootPath;
	/**
	 * 외부폴더 어플리케이션에 연결
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
			// 연결될 주소
			.addResourceHandler("/web_upload/**")
			// 타겟 폴더
			.addResourceLocations("file:///" + webrootPath)
			.setCachePeriod(30);
	}
}
