package com.grubhive.auth_test.config;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.grubhive.auth_test.utils.JwtUtil;

import java.util.Collections;
import java.util.List;


public class JwtAuthFilter extends BasicAuthenticationFilter {
    private final JwtUtil jwtUtil = new JwtUtil();

    public JwtAuthFilter() {
        super(new ProviderManager(List.of(new CustomAuthProvider())));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain chain) throws java.io.IOException, jakarta.servlet.ServletException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.parseToken(token);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, authorities));
        }
        chain.doFilter(request, response);
    }
}