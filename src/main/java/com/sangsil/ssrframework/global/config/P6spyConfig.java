package com.sangsil.ssrframework.global.config;

import com.p6spy.engine.spy.P6SpyOptions;
import com.sangsil.ssrframework.global.etc.P6spyPrettySqlFormatter;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/**
 * spy 쿼리 포맷 관리 적용방법
	1. pom
		<!-- 스파이로그 -->
		<dependency>
			<groupId>p6spy</groupId>
			<artifactId>p6spy</artifactId>
			<version>3.9.1</version>
		</dependency>
		<!-- 스프링 부트와 P6Spy 간의 자동 구성을 지원-->
		<dependency>
			<groupId>com.github.gavlyukovskiy</groupId>
			<artifactId>p6spy-spring-boot-starter</artifactId>
			<version>1.9.2</version>
		</dependency>
	2. slf4j 와 연결 파일생성
	resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
	
	3. application.yml 설정추가
	spring:
      p6spy:
        enable-logging: true   # JDBC 이벤트 로깅을 위한 P6LogFactory 등록
        multiline: true   # com.p6spy.engine.spy.appender.MultiLineFormat 사용 (SingleLineFormat 대신)
        logging: slf4j   # 기본 리스너들을 위한 로깅 사용 [slf4j, sysout, file, custom]
        # 실제 값으로 '?'를 대체한 효과적인 SQL 문자열을 추적 시스템에 보고
        # 참고: 이 설정은 로깅 메시지에 영향을 주지 않음
        tracing:
          include-parameter-values: true
	-- 여기까지 하면 파라미터에 값은 설정되지만 한줄로 나옴
	4. 쿼리 포멧 설정 P6spyPrettySqlFormatter.java
	5. 컨피그 연결 P6spyConfig.java

 */
@Configuration
public class P6spyConfig {
	@PostConstruct
	public void setLogMessageFormat() {
		P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
	}
}
