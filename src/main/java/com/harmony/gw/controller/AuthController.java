package com.harmony.gw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.gw.entity.AccountCredentials;
import com.harmony.gw.entity.Employee;
import com.harmony.gw.service.JwtService;
import com.harmony.gw.service.UserServiceImpl;

@RestController
public class AuthController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/login.do")
	public ResponseEntity<?> getTokent(@RequestBody AccountCredentials credentls) {

		UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentls.getUserId(),
				credentls.getPassword());

		Authentication auth = authenticationManager.authenticate(creds);

		String jwts = jwtService.getToken(auth.getName());

		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
	}
	
    @PostMapping("/register.do")
    public ResponseEntity<?> registerUser(@RequestBody Employee user) {
        Employee savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
