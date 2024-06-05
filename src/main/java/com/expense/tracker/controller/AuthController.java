package com.expense.tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dto.UserDto;
import com.expense.tracker.response.TokenResponse;
import com.expense.tracker.security.JWTServices;
import com.expense.tracker.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@Builder
@RequestMapping("/api/auth")
public class AuthController {

	public final JWTServices jwtServices;
	public final AuthenticationService authService;
	
	@PostMapping("/register")
	public ResponseEntity<TokenResponse> registerUSer (@Valid @RequestBody  UserDto userdto){
		return ResponseEntity.ok(authService.register(userdto));
	}
}
