package com.grubhive.auth_test.service;


import com.grubhive.auth_test.enums.Roles;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private List<UserDetails> users;

    @PostConstruct
    public void init() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        users = Arrays.asList(
                new User("user", encoder.encode("password"), Collections.singletonList(new SimpleGrantedAuthority(Roles.USER.name()))),
                new User("admin", encoder.encode("password"), Collections.singletonList(new SimpleGrantedAuthority(Roles.ADMIN.name())))
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}