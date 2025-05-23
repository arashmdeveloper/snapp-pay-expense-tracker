package com.snap.pay.expense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditAwareConfig {
	

	@Bean
	public AuditorAware<String> auditorAware() {
	    return () -> Optional.ofNullable(SecurityContextHolder.getContext())
	                        .map(SecurityContext::getAuthentication)
	                        .map(authentication -> {
	                            if (authentication.getPrincipal() instanceof UserDetails) {
	                                return ((UserDetails) authentication.getPrincipal()).getUsername();
	                            }
	                            return authentication.getName();
	                        });
	}

}
