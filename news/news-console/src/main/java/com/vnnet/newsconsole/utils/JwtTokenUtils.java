package com.vnnet.newsconsole.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnnet.newscommon.bean.HttpResult;
import com.vnnet.newscommon.utils.StringUtils;
import com.vnnet.newsconsole.security.JwtAuthenticatioToken;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component
public class JwtTokenUtils implements Serializable {


    private static UserDetailsService userDetailsService;

    private static final long serialVersionUID = 1L;

    private static final String USERNAME = Claims.SUBJECT;
    private static final String CREATED = "created";
    private static final String AUTHORITIES = "authorities";
    private static final String SECRET = "123456abcd@";
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    @Autowired
    public JwtTokenUtils(UserDetailsService userDetailsService) {
        JwtTokenUtils.userDetailsService = userDetailsService;
    }

    public static String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, MySecurityUtils.getUsername(authentication));
        claims.put(CREATED, new Date());
        return generateToken(claims);
    }

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, username);
        claims.put(CREATED, new Date());
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static Authentication getAuthenticationFromToken(HttpServletRequest request) {
        Authentication authentication = null;
        String token = JwtTokenUtils.getToken(request);
        if (!StringUtils.isBlank(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            Claims claims = getClaimsFromToken(token);
            String username = claims.getSubject();
            if (username == null) {
                return null;
            }
            if (isTokenExpired(token)) {
                return null;
            }
            if (userDetailsService == null) {
                return null;
            }
            UserDetails jwtUserDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUserDetails == null) {
                return null;
            }
            if (!validateToken(token, jwtUserDetails.getUsername())) {
                return null;
            }
            authentication = new JwtAuthenticatioToken(jwtUserDetails, null, jwtUserDetails.getAuthorities(), token);
        }
        return authentication;
    }

    private static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (MalformedJwtException | SignatureException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new AccessDeniedException("Access Denied: " + e.getMessage());
        }
    }

    public static Boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static String getToken(HttpServletRequest request) {
        String token;
        String tokenHead = "Bearer ";
        token = request.getHeader("Authorization");
        if (token != null && token.startsWith(tokenHead)) {
            token = token.substring(tokenHead.length());
        }
        if ("".equals(token)) {
            token = null;
        }
        return token;
    }



}