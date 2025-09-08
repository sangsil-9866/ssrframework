package com.sangsil.ssrframework.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 외부파일리소스경로설정(에디터이미지 등)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //static
        registry
                .addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/img/");

        // Chrome DevTools 관련 리소스 처리
        registry
                .addResourceHandler("/.well-known/**")
                .addResourceLocations("classpath:/static/.well-known/");

    }
}
