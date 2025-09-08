package com.sangsil.ssrframework.global.etc;

import com.sangsil.ssrframework.global.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 등록/수정자정보 자동자정
 */
//@Component
public class AuditAwareImpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated() ||
				authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}

		// Principal이 CustomUserDetails 타입인 경우
		if (authentication.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			return Optional.ofNullable(userDetails.getUsername());
		}

		// 다른 타입의 Principal인 경우
		return Optional.ofNullable(authentication.getName());
	}

}