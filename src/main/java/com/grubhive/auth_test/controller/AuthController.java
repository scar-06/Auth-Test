package com.grubhive.auth_test.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/demo")
public class AuthController {
    @GetMapping
    public Map<String, String> getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return java.util.Map.of("User", auth.getName(), "Role", auth.getAuthorities().iterator().next().getAuthority());
    }
}

