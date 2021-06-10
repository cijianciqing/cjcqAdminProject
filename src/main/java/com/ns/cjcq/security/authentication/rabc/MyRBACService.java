package com.ns.cjcq.security.authentication.rabc;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyRBACService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
