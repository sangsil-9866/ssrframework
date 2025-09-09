package com.sangsil.ssrframework.global.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangsil.ssrframework.domain.user.repository.UserRepository;
import com.sangsil.ssrframework.global.util.UtilMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 로그인 성공시 핸들러
 */
@Slf4j
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // 생성자로 하면 secureconfig 에서 문제생겨서 걍 했음
    private final UserRepository userRepository;
    private final UtilMessage utilMessage;

    public UserAuthenticationSuccessHandler(UserRepository userRepository, UtilMessage utilMessage) {
        this.userRepository = userRepository;
        this.utilMessage = utilMessage;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.debug("onAuthenticationSuccess 로그인 성공");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // 리다이렉트 URL 결정
        String redirectUrl = "/";
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (!targetUrl.contains(".well-known")) {
                redirectUrl = targetUrl;
            }
        }

        // ApiResponseSuccess 생성
        Map<String, Object> data = new HashMap<>();
        data.put("redirectUrl", redirectUrl);
        data.put("username", authentication.getName());

        String jsonResponse = new ObjectMapper().writeValueAsString(data);
        response.getWriter().write(jsonResponse);
    }
}
