package com.sangsil.ssrframework.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangsil.ssrframework.global.util.UtilMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 로그인 실패시 핸들러
 */
@Slf4j
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final UtilMessage utilMessage;

    public UserAuthenticationFailureHandler(UtilMessage utilMessage) {
        this.utilMessage = utilMessage;
    }

    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {

        log.debug("onAuthenticationFailure 로그인 실패");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String errorMsg = "로그인에 실패했습니다.";

        if(exception instanceof BadCredentialsException) {
            errorMsg = utilMessage.getMessage("login.fail");
        } else if(exception instanceof InternalAuthenticationServiceException){
            errorMsg = utilMessage.getMessage("exception.valid.anotation");
        }

        Map<String, Object> responseData  = new HashMap<>();
        responseData.put("message", errorMsg);

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
    }

}
