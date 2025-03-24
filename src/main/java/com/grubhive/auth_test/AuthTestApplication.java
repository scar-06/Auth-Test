package com.grubhive.auth_test;

import com.grubhive.auth_test.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthTestApplication {


	public static void main(String[] args) {
		SpringApplication.run(AuthTestApplication.class, args);
		JwtUtil jwtUtil = new JwtUtil();
		String token = jwtUtil.generateToken("user", "ROLE_ADMIN"); // or "admin", "ROLE_ADMIN"
		System.out.println("Generated Token: " + token);
	}

}
