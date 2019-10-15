package org.ioak.mirror.service;

import org.ioak.mirror.domain.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public AuthUser getAuthUser() {
        return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getAuthUserId() {
        return ((AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
