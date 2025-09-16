# ssrframework
* JAVA21, Thymeleaf, JPA, MYBATIS, POSTGRESQL
* spring-boot 3.5.5
* Spring Security
* 도매인 단위 계층형 아키텍처
* 프로퍼티 암호화
* 메세지 다국어 지원
* csrf
* jar 배포
* gradle
* QueryDsl
* actuator 서버 로그레벨변경



## 버전정보
* spring-boot: 3.5.3
* java 21


## 로그레벨 실시간 변경
> implementation 'org.springframework.boot:spring-boot-starter-actuator'

    application.yml 파일 수정
    management.endpoints.web.exposure.include: "health,info,loggers" # loggers 추가
    management.endpoint.loggers.enabled: true

    현재 로그레벨 확인
    GET http://localhost:8081/actuator/loggers

    로그 레벨 변경
    POST http://localhost:8081/actuator/loggers/com.sangsil
    Content-Type: application/json
    {
        "configuredLevel": "DEBUG"
    }

    로그 레벨 원래대로 돌리기(코드상 설정값)
    {
        "configuredLevel": null
    }


