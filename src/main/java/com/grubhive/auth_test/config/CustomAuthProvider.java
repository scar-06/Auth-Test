package com.grubhive.auth_test.config;

import com.grubhive.auth_test.entity.InMemoryUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;



@Component
class CustomAuthProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService = new InMemoryUserDetailsService();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) {
        String username = authentication.getName();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

