package com.vnnet.newsconsole.utils;


import com.vnnet.newscommon.utils.HttpUtils;
import com.vnnet.newsconsole.security.JwtAuthenticatioToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;


public class MySecurityUtils {


    public static JwtAuthenticatioToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
        JwtAuthenticatioToken token = new JwtAuthenticatioToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    public static void checkAuthentication(HttpServletRequest request) {
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public static String getUsername() {
        if(RequestContextHolder.getRequestAttributes() == null)
            return "";
        String token = JwtTokenUtils.getToken(HttpUtils.getHttpServletRequest());
        return JwtTokenUtils.getUsernameFromToken(token);
    }

    public static String getUsername(Authentication authentication) {
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Integer currentSystem;

    public static void setCurrentSystem(Integer system){
        currentSystem = system;
    }

    public static Integer getCurrentSystem(){
        return currentSystem;
    }
}
