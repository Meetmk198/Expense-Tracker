package com.expense.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.User.User;
import com.expense.tracker.dto.UserDto;
import com.expense.tracker.repo.UserRepo;
import com.expense.tracker.response.TokenResponse;
import com.expense.tracker.security.JWTServices;

import jakarta.validation.Valid;

@Service
public class AuthenticationService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	JWTServices jwtServices;

	public TokenResponse register(@Valid UserDto userdto) {
		
		var user = User.builder()
				.email(userdto.getEmail())
				.password(userdto.getPassword())
				.age(userdto.getAge())
				.mobileNo(userdto.getMobileNo())
				.dob(userdto.getDob())
				.build();
		userRepo.save(user);

		String token = jwtServices.issueToken(user);

		return TokenResponse.builder().token(token).build();
	}

}
