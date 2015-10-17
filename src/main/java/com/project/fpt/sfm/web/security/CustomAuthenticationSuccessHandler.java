package com.project.fpt.sfm.web.security;

import com.project.fpt.sfm.common.Constant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Khắc Vỹ on 10/5/2015.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("On Authentication Success");
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    /**
     * Handle login success
     *
     * @param request
     * @param response
     * @param authentication
     */
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        System.out.println("Handle");
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            System.out.println("RESPONSE HAS ALREADY COMMITTED");
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Build target url depend on logged user
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url = "/sinh-vien";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println("Role : " + authority.getAuthority());

            if (authority.getAuthority().equals(Constant.ROLE_ADMIN)) {
                url = "/admin";
            } else if (authority.getAuthority().equals(Constant.ROLE_MANAGER)) {
                url = "/manager";
            } else if (authority.getAuthority().equals(Constant.ROLE_STAFF)) {
                url = "/nhan-vien";
            }
        }

        System.out.println("Target Url : " + url);
        return url;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
