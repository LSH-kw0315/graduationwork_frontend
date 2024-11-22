package com.prompt.config;

import com.prompt.common.util.HostAddrUtil;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MicrometerConfig {
	@Bean
	public MeterRegistryCustomizer<MeterRegistry> commonTags() {
		// micrometer 공통 Tag 설정; 그라파나에서 쉽게 사용하기 위해 셋팅
		return registry -> registry.config().commonTags("serverIp", HostAddrUtil.getIp());
	}
}
