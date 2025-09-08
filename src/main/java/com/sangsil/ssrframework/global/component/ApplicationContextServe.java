package com.sangsil.ssrframework.global.component;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * application 파일 읽을수 잇게
 */
@Component
public class ApplicationContextServe implements ApplicationContextAware {
	@Getter
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(@NonNull ApplicationContext context) {
		applicationContext = context;
	}

	@PostConstruct
	public void init() {
		// Spring 컨텍스트가 완전히 초기화된 후에 실행됩니다
		if (applicationContext == null) {
			throw new IllegalStateException("ApplicationContext가 초기화되지 않았습니다.");
		}
	}
}

