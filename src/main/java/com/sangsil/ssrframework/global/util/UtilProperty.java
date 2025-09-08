package com.sangsil.ssrframework.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 프로퍼티파일 자바로 읽기
 * util 등 빈 주입 안되면 도통 잘 안들어감
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UtilProperty {
	private final Environment environment;

	public String getProperty(String propertyName) {
		return getProperty(propertyName, null);
	}

	public String getProperty(String propertyName, String defaultValue) {
		String value = defaultValue;
		if (environment.getProperty(propertyName) == null) {
			log.warn(propertyName + " properties was not loaded.");
		} else {
			value = environment.getProperty(propertyName);
		}
		return value;
	}

}
